/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Frizer;
import domen.Klijent;
import domen.Mesto;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import domen.Usluga;
import forme.GlavnaForma;
import forme.modeli.ModelTabeleRezervacija;
import forme.modeli.ModelTabeleStavke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GlavnaFormaKontroler {

    private final GlavnaForma gf;
    private int ukupnoVreme = 0;
    private double ukupanIznos = 0;

    public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    public void otvoriFormu() {
        Frizer ulogovani = koordinator.Koordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getIme() + " " + ulogovani.getPrezime();
        gf.getjLabelUsername().setText(imePrezime);
        pripremiFormu();
        gf.setVisible(true);
    }

    private void addActionListeners() {
        // kad se izabere usluga, automtaski popuni cenu
        gf.addCmbUslugaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usluga usluga = (Usluga) gf.getjComboBoxUsluga().getSelectedItem();
                if (usluga != null) {
                    gf.getjTextFieldCena().setText(String.valueOf(usluga.getCena()));
                }
            }
        });

        // Dodaj stavku
        gf.addBtnDodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (gf.getjTextFieldKolicina().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(gf, "Unesite kolicinu!");
                        return;
                    }

                    Usluga usluga = (Usluga) gf.getjComboBoxUsluga().getSelectedItem();
                    int kolicina = Integer.parseInt(gf.getjTextFieldKolicina().getText());

                    if (kolicina <= 0) {
                        JOptionPane.showMessageDialog(gf, "Kolicina mora biti veca od 0!");
                        return;
                    }

                    String opis = gf.getjTextAreaOpis().getText().trim();
                    double cena = usluga.getCena();
                    double iznos = cena * kolicina;

                    ModelTabeleStavke mts = (ModelTabeleStavke) gf.getjTableStavke().getModel();
                    int rb = mts.getRowCount() + 1;

                    StavkaRezervacije stavka = new StavkaRezervacije(rb, opis, cena, kolicina, iznos, usluga, null);
                    mts.dodajStavku(stavka);

                    // automatski izracunaj ukupno
                    ukupnoVreme += usluga.getVremeTrajanja() * kolicina;
                    ukupanIznos += iznos;

                    gf.getjTextFieldUkupnoVreme().setText(String.valueOf(ukupnoVreme));
                    gf.getjTextFieldUkupanIznos().setText(String.format("%.2f", ukupanIznos));
                    gf.getjTextFieldIznos().setText(String.format("%.2f", iznos));

                    // reset polja stavke
                    gf.getjTextFieldKolicina().setText("");
                    gf.getjTextAreaOpis().setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(gf, "Kolicina mora biti broj!");
                }
            }
        });

        // Obrisi stavku
        gf.addBtnObrisiStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = gf.getjTableStavke().getSelectedRow();
                if (row >= 0) {
                    ModelTabeleStavke mts = (ModelTabeleStavke) gf.getjTableStavke().getModel();
                    StavkaRezervacije stavka = mts.getListaStavki().get(row);

                    ukupnoVreme -= stavka.getUsluga().getVremeTrajanja() * stavka.getKolicina();
                    ukupanIznos -= stavka.getIznos();

                    gf.getjTextFieldUkupnoVreme().setText(String.valueOf(ukupnoVreme));
                    gf.getjTextFieldUkupanIznos().setText(String.format("%.2f", ukupanIznos));

                    mts.obrisiStavku(row);
                } else {
                    JOptionPane.showMessageDialog(gf, "Selektujte stavku za brisanje!");
                }
            }
        });

        // Sacuvaj rezervaciju
        gf.addBtnSacuvajRezervacijuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // validacija klijenta
                    if (gf.getjTextFieldIme().getText().isEmpty()
                            || gf.getjTextFieldPrezime().getText().isEmpty()
                            || gf.getjTextFieldBrojTelefona().getText().isEmpty()
                            || gf.getjTextFieldEmail().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(gf, "Popunite sve podatke o klijentu!");
                        return;
                    }

                    ModelTabeleStavke mts = (ModelTabeleStavke) gf.getjTableStavke().getModel();
                    if (mts.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(gf, "Morate dodati bar jednu stavku!");
                        return;
                    }

                    // kreiraj klijenta
                    Klijent klijent = new Klijent();
                    klijent.setIme(gf.getjTextFieldIme().getText());
                    klijent.setPrezime(gf.getjTextFieldPrezime().getText());
                    klijent.setBrojTelefona(gf.getjTextFieldBrojTelefona().getText());
                    klijent.setEmail(gf.getjTextFieldEmail().getText());
                    klijent.setMesto((Mesto) gf.getjComboBoxMesto().getSelectedItem());

                    // ovo mi je pravilo problem, null pointer exc
                    //komunikacija.Komunikacija.getInstance().dodajKlijenta(klijent);
                    // kreiraj rezervaciju
                    Frizer frizer = koordinator.Koordinator.getInstance().getUlogovani();
                    LocalDateTime datum = LocalDateTime.now();
                    Rezervacija rezervacija = new Rezervacija(frizer, klijent, 0, datum, ukupnoVreme, ukupanIznos);
                    rezervacija.setStavke(mts.getListaStavki());

                    komunikacija.Komunikacija.getInstance().dodajRezervaciju(rezervacija);

                    JOptionPane.showMessageDialog(gf, "Rezervacija je uspesno sacuvana!");

                    osveziFormu(mts);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(gf, "Greska: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    private void osveziFormu(ModelTabeleStavke mts) {
        mts.getListaStavki().clear();
        mts.fireTableDataChanged();
        ukupnoVreme = 0;
        ukupanIznos = 0;
        gf.getjTextFieldIme().setText("");
        gf.getjTextFieldPrezime().setText("");
        gf.getjTextFieldBrojTelefona().setText("");
        gf.getjTextFieldEmail().setText("");
        gf.getjTextFieldKolicina().setText("");
        gf.getjTextAreaOpis().setText("");
        gf.getjTextFieldCena().setText("");
        gf.getjTextFieldIznos().setText("");
        gf.getjTextFieldUkupnoVreme().setText("0");
        gf.getjTextFieldUkupanIznos().setText("0.00");
    }

    private void pripremiFormu() {
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

        gf.getjTableStavke().setModel(new ModelTabeleStavke(new ArrayList<>()));

        gf.getjTextFieldUkupnoVreme().setText("0");
        gf.getjTextFieldUkupanIznos().setText("0.00");
    }
}
