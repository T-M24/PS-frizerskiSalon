/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Klijent;
import domen.Mesto;
import forme.DodajKlijentaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola Manjencic
 */
public class DodajKlijentaKontroler {

    private final DodajKlijentaForma dkf;
    private Klijent selektovaniKlijent;

    public DodajKlijentaKontroler(DodajKlijentaForma dkf, Klijent selektovaniKlijent) {
        this.dkf = dkf;
        this.selektovaniKlijent = selektovaniKlijent;
        addActionListener();
    }

    public DodajKlijentaKontroler(DodajKlijentaForma dkf) {
        this(dkf, null);
    }

    public void otvoriFormu() {
        pripremiFormu();
        dkf.setVisible(true);
    }

    private void addActionListener() {
        dkf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ime = dkf.getjTextFieldIme().getText();
                    String prezime = dkf.getjTextFieldPrezime().getText();
                    String brojTelefona = dkf.getjTextFieldBrojTelefona().getText();
                    String email = dkf.getjTextFieldEmail().getText();
                    Mesto mesto = (Mesto) dkf.getjComboBoxMesta().getSelectedItem();

                    Klijent k = new Klijent();
                    k.setIme(ime);
                    k.setPrezime(prezime);
                    k.setBrojTelefona(brojTelefona);
                    k.setEmail(email);
                    k.setMesto(mesto);

                    komunikacija.Komunikacija.getInstance().dodajKlijenta(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je uspesno dodao klijent!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Sistem nije uspeo da doda klijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dkf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (selektovaniKlijent == null) {
                        JOptionPane.showMessageDialog(dkf, "Nije selektovan klijent za izmenu!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    selektovaniKlijent.setIme(dkf.getjTextFieldIme().getText());
                    selektovaniKlijent.setPrezime(dkf.getjTextFieldPrezime().getText());
                    selektovaniKlijent.setBrojTelefona(dkf.getjTextFieldBrojTelefona().getText());
                    selektovaniKlijent.setEmail(dkf.getjTextFieldEmail().getText());
                    selektovaniKlijent.setMesto((Mesto) dkf.getjComboBoxMesta().getSelectedItem());

                    komunikacija.Komunikacija.getInstance().izmeniKlijenta(selektovaniKlijent);
                    JOptionPane.showMessageDialog(dkf, "Klijent je uspesno izmenjen!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Sistem nije uspeo da izmeni klijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void pripremiFormu() {
        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        dkf.getjComboBoxMesta().removeAllItems();
        for (Mesto m : mesta) {
            dkf.getjComboBoxMesta().addItem(m);
        }

        //u slucaju izmene, radimo:
        if (selektovaniKlijent != null) {
            dkf.getjTextFieldIme().setText(selektovaniKlijent.getIme());
            dkf.getjTextFieldPrezime().setText(selektovaniKlijent.getPrezime());
            dkf.getjTextFieldBrojTelefona().setText(selektovaniKlijent.getBrojTelefona());
            dkf.getjTextFieldEmail().setText(selektovaniKlijent.getEmail());
            dkf.getjComboBoxMesta().setSelectedItem(selektovaniKlijent.getMesto());
        }
    }
}
