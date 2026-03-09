/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import domen.Frizer;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazKlijenataForma;
import kontroleri.LoginKontroler;
import kontroleri.GlavnaFormaKontroler;
import kontroleri.PrikazKlijenataKontroler;


public class Koordinator {

    private static Koordinator instance;
    private Frizer ulogovani; 
    private LoginKontroler loginKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    private PrikazKlijenataKontroler prikazKlijenataKontroler;
    
    public static Koordinator getInstance() {
        if (instance == null) {
            instance = new Koordinator();
        }
        return instance;
    }

    private Koordinator() {
        
    }

    public void otvoriLoginFormu() {
        loginKontroler = new LoginKontroler(new LoginForma());
        loginKontroler.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaKontroler = new GlavnaFormaKontroler(new GlavnaForma());
        glavnaFormaKontroler.otvoriFormu();
    }

    public Frizer getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Frizer ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriPrikazKlijenataForma() {
        prikazKlijenataKontroler = new PrikazKlijenataKontroler(new PrikazKlijenataForma());
        prikazKlijenataKontroler.otvoriFormu();
    }
}
