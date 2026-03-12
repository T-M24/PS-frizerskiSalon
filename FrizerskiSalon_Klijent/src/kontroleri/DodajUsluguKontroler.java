
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Usluga;
import forme.DodajUsluguForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class DodajUsluguKontroler {

    private final DodajUsluguForma duf;
    private Usluga selektovanaUsluga; 

    public DodajUsluguKontroler(DodajUsluguForma duf, Usluga selektovanaUsluga) {
        this.duf = duf;
        this.selektovanaUsluga = selektovanaUsluga;
        addActionListeners();
    }

    public DodajUsluguKontroler(DodajUsluguForma duf) {
        this(duf, null);
    }

    public void otvoriFormu() {
        pripremiFormu();
        duf.setVisible(true);
    }

    private void addActionListeners() {
        duf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (duf.getjTextFieldNaziv().getText().trim().isEmpty()
                            || duf.getjTextFieldVremeTrajanja().getText().trim().isEmpty()
                            || duf.getjTextFieldCena().getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(duf, "Sva polja su obavezna!");
                        return;
                    }
                    int vremeTrajanja = Integer.parseInt(duf.getjTextFieldVremeTrajanja().getText().trim());
                    if (vremeTrajanja <= 0) {
                        JOptionPane.showMessageDialog(duf, "Vreme trajanja mora biti vece od 0!");
                        return;
                    }
                    double cena = Double.parseDouble(duf.getjTextFieldCena().getText().trim());
                    if (cena <= 0) {
                        JOptionPane.showMessageDialog(duf, "Cena mora biti veća od 0!");
                        return;
                    }
                    Usluga usluga = new Usluga(0, duf.getjTextFieldNaziv().getText().trim(), vremeTrajanja, cena);
                    komunikacija.Komunikacija.getInstance().dodajUslugu(usluga);
                    JOptionPane.showMessageDialog(duf, "Usluga je uspesno dodata!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    koordinator.Koordinator.getInstance().osveziGlavnuFormu();
                    duf.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(duf, "Vreme trajanja i cena moraju biti brojevi!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(duf, "Greska: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        duf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (selektovanaUsluga == null) {
                        JOptionPane.showMessageDialog(duf, "Nije selektovana usluga za izmenu!", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (duf.getjTextFieldNaziv().getText().trim().isEmpty()
                            || duf.getjTextFieldVremeTrajanja().getText().trim().isEmpty()
                            || duf.getjTextFieldCena().getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(duf, "Sva polja su obavezna!");
                        return;
                    }
                    int vremeTrajanja = Integer.parseInt(duf.getjTextFieldVremeTrajanja().getText().trim());
                    if (vremeTrajanja <= 0) {
                        JOptionPane.showMessageDialog(duf, "Vreme trajanja mora biti veće od 0!");
                        return;
                    }
                    double cena = Double.parseDouble(duf.getjTextFieldCena().getText().trim());
                    if (cena <= 0) {
                        JOptionPane.showMessageDialog(duf, "Cena mora biti veća od 0!");
                        return;
                    }
                    selektovanaUsluga.setNaziv(duf.getjTextFieldNaziv().getText().trim());
                    selektovanaUsluga.setVremeTrajanja(vremeTrajanja);
                    selektovanaUsluga.setCena(cena);
                    komunikacija.Komunikacija.getInstance().izmeniUslugu(selektovanaUsluga);
                    JOptionPane.showMessageDialog(duf, "Usluga je uspesno izmenjena!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    koordinator.Koordinator.getInstance().osveziGlavnuFormu();
                    duf.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(duf, "Vreme trajanja i cena moraju biti brojevi!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(duf, "Greška: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    private void pripremiFormu() {
        if (selektovanaUsluga != null) {
            duf.getjTextFieldNaziv().setText(selektovanaUsluga.getNaziv());
            duf.getjTextFieldVremeTrajanja().setText(String.valueOf(selektovanaUsluga.getVremeTrajanja()));
            duf.getjTextFieldCena().setText(String.valueOf(selektovanaUsluga.getCena()));
            duf.prikaziDugmeIzmeni();
            duf.sakrijiDugmeDodaj();
        } else {
            duf.getjTextFieldNaziv().setText("");
            duf.getjTextFieldVremeTrajanja().setText("");
            duf.getjTextFieldCena().setText("");
            duf.sakrijiDugmeIzmeni();
            duf.prikaziDugmeDodaj();
        }
    }
}
