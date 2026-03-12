package kontroleri;

import domen.Frizer;
import domen.Klijent;
import domen.Mesto;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import domen.Usluga;
import forme.GlavnaForma;
import forme.modeli.ModelTabeleKlijent;
import forme.modeli.ModelTabeleStavke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GlavnaFormaKontroler {

    private final GlavnaForma gf;
    private int ukupnoVreme = 0;
    private double ukupanIznos = 0;
    private boolean punjenje = false; // flag da sprecimo listener tokom punjenja

    public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    public void otvoriFormu() {
        Frizer ulogovani = koordinator.Koordinator.getInstance().getUlogovani();
        gf.getjLabelUsername().setText(ulogovani.getIme() + " " + ulogovani.getPrezime());
        pripremiFormu();
        gf.setVisible(true);
    }

    private void addActionListeners() {

        gf.getjComboBoxRezervacija().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (punjenje) {
                    return;  // ovo moram zbog nullpointer exception u comboboxu
                }
                Rezervacija r = (Rezervacija) gf.getjComboBoxRezervacija().getSelectedItem();
                if (r == null) {
                    return;
                }

                Klijent k = r.getKlijent();
                gf.getjTextFieldIme().setText(k.getIme());
                gf.getjTextFieldPrezime().setText(k.getPrezime());
                gf.getjTextFieldBrojTelefona().setText(k.getBrojTelefona());
                gf.getjTextFieldEmail().setText(k.getEmail());

                if (k.getMesto() != null) {
                    for (int i = 0; i < gf.getjComboBoxMesto().getItemCount(); i++) {
                        Mesto m = gf.getjComboBoxMesto().getItemAt(i);
                        if (m != null && m.getIdMesto() == k.getMesto().getIdMesto()) {
                            gf.getjComboBoxMesto().setSelectedIndex(i);
                            break;
                        }
                    }
                }

                gf.getjTextFieldUkupnoVreme().setText(String.valueOf(r.getUkupnoVremeTrajanja()));
                gf.getjTextFieldUkupanIznos().setText(String.format("%.2f", r.getUkupanIznos()));
                gf.getjTableStavke().setModel(new ModelTabeleStavke(new ArrayList<>(r.getStavke())));

                ukupnoVreme = r.getUkupnoVremeTrajanja();
                ukupanIznos = r.getUkupanIznos();
            }
        });

        gf.addCmbUslugaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usluga usluga = (Usluga) gf.getjComboBoxUsluga().getSelectedItem();
                if (usluga != null) {
                    gf.getjTextFieldCena().setText(String.valueOf(usluga.getCena()));
                }
            }
        });

        gf.addBtnDodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rezervacija r = (Rezervacija) gf.getjComboBoxRezervacija().getSelectedItem();
                if (r == null) {
                    JOptionPane.showMessageDialog(gf, "Izaberite rezervaciju!");
                    return;
                }

                if (gf.getjTextFieldKolicina().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(gf, "Unesite kolicinu!");
                    return;
                }

                try {
                    int kolicina = Integer.parseInt(gf.getjTextFieldKolicina().getText());
                    if (kolicina <= 0) {
                        JOptionPane.showMessageDialog(gf, "Kolicina mora biti veca od 0!");
                        return;
                    }

                    Usluga usluga = (Usluga) gf.getjComboBoxUsluga().getSelectedItem();
                    String opis = gf.getjTextAreaOpis().getText().trim();
                    double cena = usluga.getCena();
                    double iznos = cena * kolicina;

                    ModelTabeleStavke mts = (ModelTabeleStavke) gf.getjTableStavke().getModel();
                    int rb = mts.getRowCount() + 1;

                    StavkaRezervacije stavka = new StavkaRezervacije(rb, opis, cena, kolicina, iznos, usluga, null);
                    mts.dodajStavku(stavka);

                    ukupnoVreme += usluga.getVremeTrajanja() * kolicina;
                    ukupanIznos += iznos;

                    gf.getjTextFieldUkupnoVreme().setText(String.valueOf(ukupnoVreme));
                    gf.getjTextFieldUkupanIznos().setText(String.format("%.2f", ukupanIznos));

                    gf.getjTextFieldKolicina().setText("");
                    gf.getjTextAreaOpis().setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(gf, "Kolicina mora biti broj!");
                }
            }
        });

        gf.addBtnObrisiStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = gf.getjTableStavke().getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(gf, "Selektujte stavku za brisanje!");
                    return;
                }

                ModelTabeleStavke mts = (ModelTabeleStavke) gf.getjTableStavke().getModel();
                StavkaRezervacije stavka = mts.getListaStavki().get(row);

                ukupnoVreme -= stavka.getUsluga().getVremeTrajanja() * stavka.getKolicina();
                ukupanIznos -= stavka.getIznos();

                gf.getjTextFieldUkupnoVreme().setText(String.valueOf(ukupnoVreme));
                gf.getjTextFieldUkupanIznos().setText(String.format("%.2f", ukupanIznos));

                mts.obrisiStavku(row);
            }
        });

        gf.addBtnSacuvajRezervacijuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Rezervacija r = (Rezervacija) gf.getjComboBoxRezervacija().getSelectedItem();
                    if (r == null) {
                        JOptionPane.showMessageDialog(gf, "Izaberite rezervaciju!");
                        return;
                    }

                    ModelTabeleStavke mts = (ModelTabeleStavke) gf.getjTableStavke().getModel();
                    if (mts.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(gf, "Morate imati bar jednu stavku!");
                        return;
                    }

                    r.setStavke(mts.getListaStavki());
                    komunikacija.Komunikacija.getInstance().izmeniRezervaciju(r);

                    JOptionPane.showMessageDialog(gf, "Rezervacija je uspesno izmenjena!");
                    pripremiFormu();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(gf, "Greska: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    private void pripremiFormu() {
        punjenje = true; // ukljuci flag

        List<Rezervacija> rezervacije = komunikacija.Komunikacija.getInstance().ucitajRezervacije();
        gf.getjComboBoxRezervacija().removeAllItems();
        for (Rezervacija r : rezervacije) {
            gf.getjComboBoxRezervacija().addItem(r);
        }

        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        gf.getjComboBoxMesto().removeAllItems();
        for (Mesto m : mesta) {
            gf.getjComboBoxMesto().addItem(m);
        }

        List<Usluga> usluge = komunikacija.Komunikacija.getInstance().ucitajUsluge();
        gf.getjComboBoxUsluga().removeAllItems();
        for (Usluga u : usluge) {
            gf.getjComboBoxUsluga().addItem(u);
        }

        punjenje = false; // iskljuci flag

        gf.getjTableStavke().setModel(new ModelTabeleStavke(new ArrayList<>()));
        gf.getjTextFieldIme().setText("");
        gf.getjTextFieldPrezime().setText("");
        gf.getjTextFieldBrojTelefona().setText("");
        gf.getjTextFieldEmail().setText("");
        gf.getjTextFieldUkupnoVreme().setText("0");
        gf.getjTextFieldUkupanIznos().setText("0.00");
        ukupnoVreme = 0;
        ukupanIznos = 0;
    }
}
