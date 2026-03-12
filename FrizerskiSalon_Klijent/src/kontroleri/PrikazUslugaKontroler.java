/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Usluga;
import forme.PrikazUslugaForma;
import forme.modeli.ModelTabeleUsluge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class PrikazUslugaKontroler {

    private final PrikazUslugaForma puf;

    public PrikazUslugaKontroler(PrikazUslugaForma puf) {
        this.puf = puf;
        addActionListener();
    }

    private void addActionListener() {
        puf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int selektovaniRed = puf.getjTableUsluga().getSelectedRow();
                if (selektovaniRed == -1) {
                    javax.swing.JOptionPane.showMessageDialog(puf, "Selektujte uslugu za izmenu!", "Greška", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleUsluge mtu = (ModelTabeleUsluge) puf.getjTableUsluga().getModel();
                Usluga u = mtu.getListaSvihUsluga().get(selektovaniRed);
                koordinator.Koordinator.getInstance().otvoriIzmeniUsluguFormu(u);
            }
        });
        puf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = puf.getjTableUsluga().getSelectedRow();
                if (selektovaniRed == -1) {
                    javax.swing.JOptionPane.showMessageDialog(puf, "Selektujte uslugu za brisanje!", "Greška", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    ModelTabeleUsluge mtu = (ModelTabeleUsluge) puf.getjTableUsluga().getModel();
                    Usluga u = mtu.getListaSvihUsluga().get(selektovaniRed);
                    
                    try {
                        komunikacija.Komunikacija.getInstance().obrisiUslugu(u);
                        koordinator.Koordinator.getInstance().osveziGlavnuFormu();
                        pripremiFormu();
                        JOptionPane.showMessageDialog(puf, "Sistem je uspešno obrisao uslugu!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(puf, "Sistem nije uspeo da obriše uslugu!", "Greška", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace(); //ConstraintViolationException
                    }
                }
            }
        });
    }

    public void otvoriFormu() {
        pripremiFormu();
        puf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Usluga> usluge = komunikacija.Komunikacija.getInstance().ucitajUsluge();
        ModelTabeleUsluge mtu = new ModelTabeleUsluge(usluge);
        puf.getjTableUsluga().setModel(mtu);
    }

}
