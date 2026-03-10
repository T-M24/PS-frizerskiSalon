/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Frizer;
import domen.Klijent;
import domen.Mesto;
import domen.Rezervacija;
import java.util.List;
import komunikacija.Zahtev;
import operacija.klijenti.DodajKlijentaSO;
import operacija.klijenti.IzmeniKlijentaSO;
import operacija.klijenti.ObrisiKlijentaSO;
import operacija.login.LoginOperacija;
import operacija.klijenti.UcitajKlijenteSO;
import operacija.mesta.UcitajMestaSO;
import operacije.rezervacija.UcitajRezervacijeSO;

/**
 *
 * @author Nikola Manjencic
 */
public class Kontroler {
    private static Kontroler instance;
    public static Kontroler getInstance(){
        if(instance == null){
            instance = new Kontroler();
        }
        return instance;
    }
    
    private Kontroler(){
        
    }

    public Frizer login(Frizer frizer) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(frizer, null); 
        System.out.println("Kontroler: "+ operacija.getFrizer());
        return operacija.getFrizer();
    }

    public List<Klijent> ucitajKlijente() throws Exception {
        UcitajKlijenteSO operacija = new UcitajKlijenteSO();
        operacija.izvrsi(null, null);
        System.out.println("Kontroler: " + operacija.getKlijenti());
        return operacija.getKlijenti();
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        ObrisiKlijentaSO operacija = new ObrisiKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO operacija = new UcitajMestaSO();
        operacija.izvrsi(null, null);
        return operacija.getMesta();
    }
    
    public void dodajKlijenta(Klijent k) throws Exception{
        DodajKlijentaSO operacija = new DodajKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public void izmeniKlijenta(Klijent k) throws Exception {
        IzmeniKlijentaSO operacija = new IzmeniKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public List<Rezervacija> ucitajRezervacije() throws Exception{
        UcitajRezervacijeSO operacija = new UcitajRezervacijeSO();
        operacija.izvrsi(null, null);
        return operacija.getRezervacije();
    }
    
}
