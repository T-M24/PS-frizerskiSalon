/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Klijent;
import forme.GlavnaForma;
import forme.PrikazKlijenataForma;
import forme.modeli.ModelTabeleKlijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola Manjencic
 */
public class PrikazKlijenataKontroler {

    private final PrikazKlijenataForma pkf;

    public PrikazKlijenataKontroler(PrikazKlijenataForma pkf) {
        this.pkf = pkf;
        addActionListener();
    }

    private void addActionListener() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = pkf.getjTableKlijenti().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pkf, "Sistem nije uspeo da obrise klijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                    Klijent k = mtk.getListaSvihKlijenata().get(selektovaniRed);
                    try {
                        komunikacija.Komunikacija.getInstance().obrisiKlijenta(k);
                        pripremiFormu();
                        JOptionPane.showMessageDialog(pkf, "Sistem je uspesno obrisao klijenta!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pkf, "Sistem nije uspeo da obrise klijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace(); //ConstraintViolationException
                    }
                }
            }
        });
        pkf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = pkf.getjTableKlijenti().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pkf, "Selektujte klijenta za izmenu!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                Klijent k = mtk.getListaSvihKlijenata().get(selektovaniRed);
                koordinator.Koordinator.getInstance().otvoriIzmeniKlijentaFormu(k);
            }
        });

    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Klijent> klijenti = komunikacija.Komunikacija.getInstance().ucitajKlijente();
        ModelTabeleKlijent mtk = new ModelTabeleKlijent(klijenti);
        pkf.getjTableKlijenti().setModel(mtk);
    }

}
