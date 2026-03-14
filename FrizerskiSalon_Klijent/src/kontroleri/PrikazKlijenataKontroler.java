/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Klijent;
import domen.Mesto;
import forme.GlavnaForma;
import forme.PrikazKlijenataForma;
import forme.modeli.ModelTabeleKlijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

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
                    JOptionPane.showMessageDialog(pkf, "Sistem nije uspeo da obriše klijenta!", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                    Klijent k = mtk.getListaSvihKlijenata().get(selektovaniRed);
                    try {
                        komunikacija.Komunikacija.getInstance().obrisiKlijenta(k);
                        koordinator.Koordinator.getInstance().osveziGlavnuFormu();
                        pripremiFormu();
                        JOptionPane.showMessageDialog(pkf, "Sistem je uspešno obrisao klijenta!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pkf, "Sistem nije uspeo da obriše klijenta, postoji rezervacija za istog!", "Greška", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(pkf, "Selektujte klijenta za izmenu!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                Klijent k = mtk.getListaSvihKlijenata().get(selektovaniRed);
                koordinator.Koordinator.getInstance().otvoriIzmeniKlijentaFormu(k);
            }
        });

        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pkf.getjTextFieldIme().getText();
                String prezime = pkf.getjTextFieldPrezime().getText();
                Mesto mesto = (Mesto) pkf.getjComboBoxMesto().getSelectedItem();

                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                boolean nadjen = mtk.pretrazi(ime, prezime, mesto);

                if (!nadjen) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe klijente po zadatim kriterijumima!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem je našao klijente po zadatim kriterijumima!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        pkf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = pkf.getjTableKlijenti().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe klijenta!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijenti().getModel();
                Klijent k = mtk.getListaSvihKlijenata().get(selektovaniRed);

                String detalji = "Ime: " + k.getIme() + "\n"
                        + "Prezime: " + k.getPrezime() + "\n"
                        + "Broj telefona: " + k.getBrojTelefona() + "\n"
                        + "Email: " + k.getEmail() + "\n"
                        + "Mesto: " + (k.getMesto() != null ? k.getMesto().getNaziv() : "N/A");

                JOptionPane.showMessageDialog(pkf, detalji, "Sistem je našao klijenta!", JOptionPane.INFORMATION_MESSAGE);
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

        List<Mesto> mesta = komunikacija.Komunikacija.getInstance().ucitajMesta();
        pkf.getjComboBoxMesto().removeAllItems();
        //ovo dodajem da ne ogranicavam korisnika da mora da pretrazuje po mestu!
        pkf.getjComboBoxMesto().addItem(null);
        for (Mesto m : mesta) {
            pkf.getjComboBoxMesto().addItem(m);
        }
    }

    public void osveziTabelu() {
        pripremiFormu();
    }

}
