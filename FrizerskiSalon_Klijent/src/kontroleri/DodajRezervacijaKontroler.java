package kontroleri;

import domen.Frizer;
import domen.Klijent;
import domen.Mesto;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import domen.Usluga;
import forme.DodajRezervacijaForma;
import forme.modeli.ModelTabeleStavke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DodajRezervacijaKontroler {

    private final DodajRezervacijaForma drf;
    private int ukupnoVreme = 0;
    private double ukupanIznos = 0;

    public DodajRezervacijaKontroler(DodajRezervacijaForma drf) {
        this.drf = drf;
        addActionListener();
    }

    private void addActionListener() {

        // kad se izabere usluga, automatski popuni cenu
        drf.addCmbUslugaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usluga usluga = (Usluga) drf.getjComboBoxUsluga().getSelectedItem();
                if (usluga != null) {
                    drf.getjTextFieldCena().setText(String.valueOf(usluga.getCena()));
                }
            }
        });

        // Dodaj stavku
        drf.addBtnDodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (drf.getjTextFieldKolicina().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(drf, "Unesite količinu!");
                        return;
                    }

                    int kolicina = Integer.parseInt(drf.getjTextFieldKolicina().getText());
                    if (kolicina <= 0) {
                        JOptionPane.showMessageDialog(drf, "Količina mora biti veća od 0!");
                        return;
                    }

                    Usluga usluga = (Usluga) drf.getjComboBoxUsluga().getSelectedItem();
                    String opis = drf.getjTextAreaOpis().getText().trim();
                    double cena = usluga.getCena();
                    double iznos = cena * kolicina;

                    ModelTabeleStavke mts = (ModelTabeleStavke) drf.getjTableStavke().getModel();
                    int rb = mts.getRowCount() + 1;

                    StavkaRezervacije stavka = new StavkaRezervacije(rb, opis, cena, kolicina, iznos, usluga, null);
                    mts.dodajStavku(stavka);

                    ukupnoVreme += usluga.getVremeTrajanja() * kolicina;
                    ukupanIznos += iznos;

                    drf.getjTextFieldUkupnoVreme().setText(String.valueOf(ukupnoVreme));
                    drf.getjTextFieldUkupanIznos().setText(String.format("%.2f", ukupanIznos));

                    drf.getjTextFieldKolicina().setText("");
                    drf.getjTextAreaOpis().setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(drf, "Količina mora biti broj!");
                }
            }
        });

        // Obrisi stavku
        drf.addBtnObrisiStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = drf.getjTableStavke().getSelectedRow();
                if (row >= 0) {
                    ModelTabeleStavke mts = (ModelTabeleStavke) drf.getjTableStavke().getModel();
                    StavkaRezervacije stavka = mts.getListaStavki().get(row);

                    ukupnoVreme -= stavka.getUsluga().getVremeTrajanja() * stavka.getKolicina();
                    ukupanIznos -= stavka.getIznos();

                    drf.getjTextFieldUkupnoVreme().setText(String.valueOf(ukupnoVreme));
                    drf.getjTextFieldUkupanIznos().setText(String.format("%.2f", ukupanIznos));

                    mts.obrisiStavku(row);
                } else {
                    JOptionPane.showMessageDialog(drf, "Selektujte stavku za brisanje!");
                }
            }
        });

        // Sacuvaj rezervaciju
        drf.addBtnDodajRezervacijuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // validacija klijenta
                    if (drf.getjTextFieldIme().getText().isEmpty()
                            || drf.getjTextFieldPrezime().getText().isEmpty()
                            || drf.getjTextFieldBrojTelefona().getText().isEmpty()
                            || drf.getjTextFieldEmail().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(drf, "Popunite sve podatke o klijentu!");
                        return;
                    }

                    // validacija datuma
                    if (drf.getjTextFieldDatum().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(drf, "Unesite datum rezervacije!");
                        return;
                    }

                    LocalDateTime datum;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        datum = LocalDateTime.parse(drf.getjTextFieldDatum().getText(), formatter);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(drf, "Datum mora biti u formatu dd-MM-yyyy!");
                        return;
                    }

                    // validacija stavki
                    ModelTabeleStavke mts = (ModelTabeleStavke) drf.getjTableStavke().getModel();
                    if (mts.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(drf, "Morate dodati bar jednu stavku!");
                        return;
                    }

                    // kreiraj klijenta
                    Klijent klijent = new Klijent();
                    klijent.setIme(drf.getjTextFieldIme().getText());
                    klijent.setPrezime(drf.getjTextFieldPrezime().getText());
                    klijent.setBrojTelefona(drf.getjTextFieldBrojTelefona().getText());
                    klijent.setEmail(drf.getjTextFieldEmail().getText());
                    klijent.setMesto((Mesto) drf.getjComboBoxMesto().getSelectedItem());

                    Frizer frizer = (Frizer) drf.getjComboBoxFrizer().getSelectedItem();

                    Rezervacija rezervacija = new Rezervacija(frizer, klijent, 0, datum, ukupnoVreme, ukupanIznos);
                    rezervacija.setStavke(mts.getListaStavki());

                    komunikacija.Komunikacija.getInstance().dodajRezervaciju(rezervacija);

                    JOptionPane.showMessageDialog(drf, "Rezervacija je uspešno sačuvana!");
                    koordinator.Koordinator.getInstance().osveziGlavnuFormu();
                    osveziFormu(mts);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(drf, "Greška: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    public void otvoriFormu() {
        pripremiFormu();
        drf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        drf.getjComboBoxMesto().removeAllItems();
        for (Mesto m : mesta) {
            drf.getjComboBoxMesto().addItem(m);
        }

        List<Usluga> usluge = komunikacija.Komunikacija.getInstance().ucitajUsluge();
        drf.getjComboBoxUsluga().removeAllItems();
        for (Usluga u : usluge) {
            drf.getjComboBoxUsluga().addItem(u);
        }

        List<Frizer> frizeri = komunikacija.Komunikacija.getInstance().ucitajFrizere();
        drf.getjComboBoxFrizer().removeAllItems();
        for (Frizer f : frizeri) {
            drf.getjComboBoxFrizer().addItem(f);
        }

        drf.getjTableStavke().setModel(new ModelTabeleStavke(new ArrayList<>()));
        drf.getjTextFieldUkupnoVreme().setText("0");
        drf.getjTextFieldUkupanIznos().setText("0.00");
        ukupnoVreme = 0;
        ukupanIznos = 0;
    }

    private void osveziFormu(ModelTabeleStavke mts) {
        mts.getListaStavki().clear();
        mts.fireTableDataChanged();
        ukupnoVreme = 0;
        ukupanIznos = 0;
        drf.getjTextFieldIme().setText("");
        drf.getjTextFieldPrezime().setText("");
        drf.getjTextFieldBrojTelefona().setText("");
        drf.getjTextFieldEmail().setText("");
        drf.getjTextFieldDatum().setText("");
        drf.getjTextFieldKolicina().setText("");
        drf.getjTextAreaOpis().setText("");
        drf.getjTextFieldCena().setText("");
        drf.getjTextFieldUkupnoVreme().setText("0");
        drf.getjTextFieldUkupanIznos().setText("0.00");
    }
}
