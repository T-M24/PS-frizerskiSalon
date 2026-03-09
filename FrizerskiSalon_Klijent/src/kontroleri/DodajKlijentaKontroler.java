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
    
    public DodajKlijentaKontroler(DodajKlijentaForma dkf) {
        this.dkf = dkf;
        addActionListener();
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
    }
    
    private void pripremiFormu() {
        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        dkf.getjComboBoxMesta().removeAllItems();
        for (Mesto m : mesta) {
            dkf.getjComboBoxMesta().addItem(m);
        }
    }
}
