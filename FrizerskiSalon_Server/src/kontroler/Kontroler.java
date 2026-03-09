/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Frizer;
import domen.Klijent;
import domen.Mesto;
import java.util.List;
import komunikacija.Zahtev;
import operacija.klijenti.DodajKlijentaSO;
import operacija.klijenti.ObrisiKlijentaSO;
import operacija.login.LoginOperacija;
import operacija.klijenti.UcitajKlijenteSO;
import operacija.mesta.UcitajMestaSO;

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

   
}
