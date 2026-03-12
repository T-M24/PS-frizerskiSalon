/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Frizer;
import domen.Klijent;
import domen.Mesto;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import domen.Usluga;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
        if (odgovor.getResponse() == null) {
            System.out.println("Sistem je uspesno obrisao klijenta!");
        } else {
            System.out.println("Sistem nije mogao da obriše klijenta!");
            ((Exception) odgovor.getResponse()).printStackTrace();
            throw new Exception("Sistem nije mogao da obriše klijenta!");
        }
    }

    public List<Mesto> ucitajMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        return (List<Mesto>) odgovor.getResponse();
    }

    public void dodajKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLIJENTA, k);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if (odgovor.getResponse() != null) {
            throw new Exception("Sistem nije mogao da doda klijenta!");
        }
    }

    public void izmeniKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_KLIJENTA, k);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if (odgovor.getResponse() == null) {
            System.out.println("Sistem je uspesno izmenio klijenta!");
        } else {
            ((Exception) odgovor.getResponse()).printStackTrace();
            throw new Exception("Sistem nije mogao da izmeni korisnika!");
        }
    }

    public List<Rezervacija> ucitajRezervacije() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_REZERVACIJE, null);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        return (List<Rezervacija>) odgovor.getResponse();
    }

    public void dodajRezervaciju(Rezervacija rezervacija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_REZERVACIJU, rezervacija);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if (odgovor.getResponse() != null) {
            Exception ex = (Exception) odgovor.getResponse();
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }

    public List<Usluga> ucitajUsluge() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_USLUGE, null);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        return (List<Usluga>) odgovor.getResponse();
    }

    public List<Frizer> ucitajFrizere() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_FRIZERA, null);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        return (List<Frizer>) odgovor.getResponse();
    }

    public void izmeniRezervaciju(Rezervacija r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_REZERVACIJU, r);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if (odgovor.getResponse() != null) {
            Exception ex = (Exception) odgovor.getResponse();
            throw new Exception(ex.getMessage());
        }
    }

    public void dodajUslugu(Usluga usluga) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_USLUGU, usluga);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if (odgovor.getResponse() != null) {
            Exception ex = (Exception) odgovor.getResponse();
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }

    public void izmeniUslugu(Usluga u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_USLUGU, u);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if (odgovor.getResponse() != null) {
            throw new Exception(((Exception) odgovor.getResponse()).getMessage());
        }
    }

    public void obrisiUslugu(Usluga u) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_USLUGU, u);
        posiljalac.send(zahtev);
        Odgovor odgovor = (Odgovor) primalac.accept();
        if (odgovor.getResponse() == null) {
            System.out.println("Sistem je uspešno obrisao klijenta!");
        } else {
            System.out.println("Sistem nije mogao da obriše klijenta!");
            ((Exception) odgovor.getResponse()).printStackTrace();
            throw new Exception("Sistem nije mogao da obriše klijenta!");
        }
    }

}
