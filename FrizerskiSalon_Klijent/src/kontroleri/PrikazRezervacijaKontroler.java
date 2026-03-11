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
                String imeFrizera = prf.getjTextFieldImeFrizera().getText().trim();
                ModelTabeleRezervacija mtr = (ModelTabeleRezervacija) prf.getjTableRezervacija().getModel();
                mtr.pretrazi(imeFrizera);
            }
        });
    }

    private void pripremiFormu() {
        List<Rezervacija> rezervacije = komunikacija.Komunikacija.getInstance().ucitajRezervacije();
        ModelTabeleRezervacija mtr = new ModelTabeleRezervacija(rezervacije);
        prf.getjTableRezervacija().setModel(mtr);
    }
}
