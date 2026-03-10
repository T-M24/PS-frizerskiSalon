/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Frizer;
import domen.Rezervacija;
import forme.GlavnaForma;
import forme.modeli.ModelTabeleRezervacija;
import java.util.List;

/**
 *
 * @author Nikola Manjencic
 */
public class GlavnaFormaKontroler {

    private final GlavnaForma gf;

    public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    public void otvoriFormu() {
        Frizer ulogovani = koordinator.Koordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getIme() + " " + ulogovani.getPrezime();
        gf.getjLabelImeKorisnika().setText(imePrezime);
        pripremiFormu();
        gf.setVisible(true);
    }

    private void addActionListeners() {
        //IMPLEMENTIRATI POSLE!!!!
    }

    private void pripremiFormu() {
        List<Rezervacija> rezervacije = komunikacija.Komunikacija.getInstance().ucitajRezervacije();
        ModelTabeleRezervacija mtr = new ModelTabeleRezervacija(rezervacije);
        gf.getjTableRezervacije().setModel(mtr);
    }

}
