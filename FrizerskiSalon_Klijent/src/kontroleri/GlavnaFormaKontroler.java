/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Frizer;
import forme.GlavnaForma;

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
        String imePrezime = ulogovani.getIme()+ " " + ulogovani.getPrezime();
        gf.setVisible(true);
        gf.getjLabelImeKorisnika().setText(imePrezime);
    }

    private void addActionListeners() {
        //IMPLEMENTIRATI POSLE!!!!
    }
    
     
}
