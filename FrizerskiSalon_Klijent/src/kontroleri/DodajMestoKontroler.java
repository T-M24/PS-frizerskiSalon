/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Mesto;
import forme.DodajMestoForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DodajMestoKontroler {

    private final DodajMestoForma dmf;

    public DodajMestoKontroler(DodajMestoForma dmf) {
        this.dmf = dmf;
        addActionListeners();
    }

    private void addActionListeners() {
        dmf.addBtnDodajMestoActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String naziv = dmf.getjTextFieldNaziv().getText();
                    String postanskiBroj = dmf.getjTextFieldPostanskiBroj().getText();
                    Mesto m = new Mesto();
                    m.setNaziv(naziv);
                    m.setPostanskiBroj(postanskiBroj);

                    komunikacija.Komunikacija.getInstance().dodajMesto(m);
                    JOptionPane.showMessageDialog(dmf, "Sistem je uspešno dodao mesto!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dmf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dmf, "Sistem nije uspeo da doda mesto!", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        osveziFormu();
        dmf.setVisible(true);
    }

    private void osveziFormu() {
        dmf.getjTextFieldNaziv().setText("");
        dmf.getjTextFieldPostanskiBroj().setText("");
    }

}
