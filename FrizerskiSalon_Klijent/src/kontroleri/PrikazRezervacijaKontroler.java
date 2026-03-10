/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Rezervacija;
import forme.PrikazRezervacijaForma;
import forme.modeli.ModelTabeleRezervacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola Manjencic
 */
public class PrikazRezervacijaKontroler {

    private final PrikazRezervacijaForma prf;

    public PrikazRezervacijaKontroler(PrikazRezervacijaForma prf) {
        this.prf = prf;
    }

    public void otvoriFormu() {
        pripremiFormu();
        prf.setVisible(true);
    }

    private void addActionListeners() {
        prf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = prf.getjTableRezervacija().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(prf, "Selektujte rezervaciju!", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleRezervacija mtr = (ModelTabeleRezervacija) prf.getjTableRezervacija().getModel();
                Rezervacija r = mtr.getListaRezervacija().get(selektovaniRed);
                koordinator.Koordinator.getInstance().otvoriDetaljeRezervacije(r);
            }
        });
    }

    private void pripremiFormu() {
        List<Rezervacija> rezervacije = komunikacija.Komunikacija.getInstance().ucitajRezervacije();
        ModelTabeleRezervacija mtr = new ModelTabeleRezervacija(rezervacije);
        prf.getjTableRezervacija().setModel(mtr);
    }
}
