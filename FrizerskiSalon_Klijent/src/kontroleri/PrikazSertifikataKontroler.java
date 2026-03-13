/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Frizer;
import domen.Sertifikat;
import forme.PrikazSertifikataForma;
import forme.modeli.ModelTabeleSertifikat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PrikazSertifikataKontroler {

    private final PrikazSertifikataForma psf;

    public PrikazSertifikataKontroler(PrikazSertifikataForma psf) {
        this.psf = psf;
        addActionListeners();
    }

    private void addActionListeners() {
        psf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = psf.getjTextFieldNaziv().getText().trim();
                String institucija = psf.getjTextFieldInstitucija().getText().trim();
                ModelTabeleSertifikat mts = (ModelTabeleSertifikat) psf.getjTableSertifikati().getModel();
                mts.pretrazi(naziv, institucija);
            }
        });
    }

    public void otvoriFormu() {
        pripremiFormu();
        psf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Sertifikat> sertifikati = komunikacija.Komunikacija.getInstance().ucitajSertifikate();
        ModelTabeleSertifikat mts = new ModelTabeleSertifikat(sertifikati);
        psf.getjTableSertifikati().setModel(mts);
    }
}
