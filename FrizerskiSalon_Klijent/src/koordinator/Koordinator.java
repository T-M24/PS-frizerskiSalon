/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import domen.Frizer;
import domen.Klijent;
import domen.Rezervacija;
import forme.DodajKlijentaForma;
import forme.DodajRezervacijaForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazKlijenataForma;
import forme.PrikazRezervacijaForma;
import forme.PrikazUslugaForma;
import kontroleri.DodajKlijentaKontroler;
import kontroleri.DodajRezervacijaKontroler;
import kontroleri.LoginKontroler;
import kontroleri.GlavnaFormaKontroler;
import kontroleri.PrikazKlijenataKontroler;
import kontroleri.PrikazRezervacijaKontroler;
import kontroleri.PrikazUslugaKontroler;

public class Koordinator {

    private static Koordinator instance;
    private Frizer ulogovani;
    private LoginKontroler loginKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    private PrikazKlijenataKontroler prikazKlijenataKontroler;
    private DodajKlijentaKontroler dodajKlijentaKontroler;
    private PrikazRezervacijaKontroler prikazRezervacijaKontroler;
    private DodajRezervacijaKontroler dodajRezervacijaKontroler;
    private PrikazUslugaKontroler prikazUslugaKontroler;

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

    public void otvoriDodajKlijentaFormu() {
        dodajKlijentaKontroler = new DodajKlijentaKontroler(new DodajKlijentaForma());
        dodajKlijentaKontroler.otvoriFormu();
    }

    public void otvoriIzmeniKlijentaFormu(Klijent k) {
        dodajKlijentaKontroler = new DodajKlijentaKontroler(new DodajKlijentaForma(), k);
        dodajKlijentaKontroler.otvoriFormu();
    }

    public void osveziKlijente() {
        if (prikazKlijenataKontroler != null) {
            prikazKlijenataKontroler.osveziTabelu();
        }
    }

    public void otvoriPrikaziRezervacijaForma(){
        prikazRezervacijaKontroler = new PrikazRezervacijaKontroler(new PrikazRezervacijaForma());
        prikazRezervacijaKontroler.otvoriFormu();
    }

    public void otvoriDodajRezervacijaForma() {
        dodajRezervacijaKontroler = new DodajRezervacijaKontroler(new DodajRezervacijaForma());
        dodajRezervacijaKontroler.otvoriFormu();
    }

    public void otvoriPrikazUslugaForma() {
        prikazUslugaKontroler = new PrikazUslugaKontroler(new PrikazUslugaForma());
        prikazUslugaKontroler.otvoriFormu();
    }

    public void otvoriDodajUsluguForma() {
        
    }

}
