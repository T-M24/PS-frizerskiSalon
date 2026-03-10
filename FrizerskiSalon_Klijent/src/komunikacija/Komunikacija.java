/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Frizer;
import domen.Klijent;
import domen.Mesto;
import domen.Rezervacija;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola Manjencic
 */
public class Komunikacija {

    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    private Komunikacija() {

    }

    public void konekcija() throws Exception {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Frizer login(String username, String password) {
        Frizer frizer = new Frizer();
        frizer.setKorisnickoIme(username);
        frizer.setSifra(password);

        Zahtev zahtev = new Zahtev(Operacija.LOGIN, frizer);
        posiljalac.send(zahtev);

        Odgovor odgovor = (Odgovor) primalac.accept();

        return (Frizer) odgovor.getResponse();
    }

    public List<Klijent> ucitajKlijente() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KLIJENTE, null);
        List<Klijent> klijenti = new ArrayList<>();
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        klijenti = (List<Klijent>) odgovor.getResponse();
        return klijenti;
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KLIJENTA, k);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if(odgovor.getResponse() == null){
            System.out.println("Sistem je uspesno obrisao klijenta!");
        } else{
            System.out.println("Sistem nije mogao da obrise klijenta!");
            ((Exception)odgovor.getResponse()).printStackTrace();
            throw new Exception("Sistem nije mogao da obrise klijenta!");
        }
    }

    public List<Mesto> ucitajMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        return (List<Mesto>) odgovor.getResponse();
    }
    
    public void dodajKlijenta(Klijent k) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLIJENTA,k);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if(odgovor.getResponse()!=null){
            throw new Exception("Sistem nije mogao da doda klijenta!");
        }
    }

    public void izmeniKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_KLIJENTA, k);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if(odgovor.getResponse() == null){
            System.out.println("Sistem je uspesno izmenio klijenta!");
        } else{
            ((Exception)odgovor.getResponse()).printStackTrace();
            throw new Exception("Sistem nije mogao da zimeni korisnika!");
        }
    }
    
    public List<Rezervacija> ucitajRezervacije(){
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_REZERVACIJE,null);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        return (List<Rezervacija>) odgovor.getResponse();
    }
    
}
