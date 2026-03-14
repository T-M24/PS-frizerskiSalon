/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Klijent;
import domen.Frizer;
import domen.Rezervacija;
import domen.Usluga;
import forme.DetaljiRezervacijeForma;
import forme.PrikazRezervacijaForma;
import forme.modeli.ModelTabeleRezervacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class PrikazRezervacijaKontroler {

    private final PrikazRezervacijaForma prf;

    public PrikazRezervacijaKontroler(PrikazRezervacijaForma prf) {
        this.prf = prf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        prf.setVisible(true);
    }

    private void addActionListeners() {
        prf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frizer frizer = (Frizer) prf.getjComboBoxFrizer().getSelectedItem();
                Klijent klijent = (Klijent) prf.getjComboBoxKlijent().getSelectedItem();
                Usluga usluga = (Usluga) prf.getjComboBoxUsluga().getSelectedItem();

                ModelTabeleRezervacija mtr = (ModelTabeleRezervacija) prf.getjTableRezervacija().getModel();
                boolean nadjena = mtr.pretrazi(frizer, klijent, usluga);

                if (!nadjena) {
                    JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe rezervacije po zadatim kriterijumima!", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(prf, "Sistem je našao rezervacije po zadatim kriterijumima!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        prf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = prf.getjTableRezervacija().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(prf, "Selektujte rezervaciju za brisanje!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleRezervacija mtr = (ModelTabeleRezervacija) prf.getjTableRezervacija().getModel();
                Rezervacija r = mtr.getListaRezervacija().get(selektovaniRed);
                try {
                    komunikacija.Komunikacija.getInstance().obrisiRezervaciju(r);
                    koordinator.Koordinator.getInstance().osveziGlavnuFormu();
                    pripremiFormu();
                    JOptionPane.showMessageDialog(prf, "Sistem je uspešno obrisao rezervaciju!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(prf, "Sistem nije uspeo da obriše rezervaciju!", "Greška", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        prf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = prf.getjTableRezervacija().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe rezervaciju!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleRezervacija mtr = (ModelTabeleRezervacija) prf.getjTableRezervacija().getModel();
                Rezervacija r = mtr.getListaRezervacija().get(selektovaniRed);
                JOptionPane.showMessageDialog(prf, "Sistem je našao rezervaciju!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                DetaljiRezervacijeForma drf = new DetaljiRezervacijeForma(r);
                drf.setVisible(true);
            }
        });
    }

    private void pripremiFormu() {
        List<Rezervacija> rezervacije = komunikacija.Komunikacija.getInstance().ucitajRezervacije();
        ModelTabeleRezervacija mtr = new ModelTabeleRezervacija(rezervacije);
        prf.getjTableRezervacija().setModel(mtr);

        List<Frizer> frizeri = komunikacija.Komunikacija.getInstance().ucitajFrizere();
        prf.getjComboBoxFrizer().removeAllItems();
        prf.getjComboBoxFrizer().addItem(null);
        for (Frizer f : frizeri) {
            prf.getjComboBoxFrizer().addItem(f);
        }

        List<Klijent> klijenti = komunikacija.Komunikacija.getInstance().ucitajKlijente();
        prf.getjComboBoxKlijent().removeAllItems();
        prf.getjComboBoxKlijent().addItem(null);
        for (Klijent k : klijenti) {
            prf.getjComboBoxKlijent().addItem(k);
        }

        List<Usluga> usluge = komunikacija.Komunikacija.getInstance().ucitajUsluge();
        prf.getjComboBoxUsluga().removeAllItems();
        prf.getjComboBoxUsluga().addItem(null);
        for (Usluga u : usluge) {
            prf.getjComboBoxUsluga().addItem(u);
        }
    }
}
