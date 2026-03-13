/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Sertifikat;
import forme.DodajSertifikatForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class DodajSertifikatKontroler {

    private final DodajSertifikatForma dsf;

    public DodajSertifikatKontroler(DodajSertifikatForma dsf) {
        this.dsf = dsf;
        addActionListeners();
    }

    private void addActionListeners() {
        dsf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String naziv = dsf.getjTextFieldNaziv().getText().trim();
                    String institucija = dsf.getjTextFieldInstitucija().getText().trim();

                    if (naziv.isEmpty() || institucija.isEmpty()) {
                        JOptionPane.showMessageDialog(dsf, "Sva polja su obavezna!");
                        return;
                    }

                    Sertifikat sertifikat = new Sertifikat();
                    sertifikat.setNaziv(naziv);
                    sertifikat.setInstitucija(institucija);

                    komunikacija.Komunikacija.getInstance().dodajSertifikat(sertifikat);
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio sertifikat!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    osveziFormu();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti sertifikat!", "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public void otvoriFormu() {
        osveziFormu();
        dsf.setVisible(true);
    }

    private void osveziFormu() {
        dsf.getjTextFieldNaziv().setText("");
        dsf.getjTextFieldInstitucija().setText("");
    }
}
