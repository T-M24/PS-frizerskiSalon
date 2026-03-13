/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Frizer;
import domen.Klijent;
import domen.Mesto;
import domen.Rezervacija;
import domen.Sertifikat;
import domen.StavkaRezervacije;
import domen.Usluga;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Zahtev;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import kontroler.Kontroler;

public class ObradaKlijentskihZahteva extends Thread {

    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.accept();
                ////////////////// NAKNADNO DODATO, proveriti da li sme ovako?
                if (zahtev == null) {
                    kraj = true;
                    break;
                }
                ////////////////
                Odgovor odgovor = new Odgovor();
                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Frizer frizer = (Frizer) zahtev.getParametar();
                        frizer = kontroler.Kontroler.getInstance().login(frizer);
                        odgovor.setResponse(frizer);
                        break;
                    case UCITAJ_KLIJENTE:
                        List<Klijent> klijenti = kontroler.Kontroler.getInstance().ucitajKlijente();
                        odgovor.setResponse(klijenti);
                        break;
                    case OBRISI_KLIJENTA:
                        try {
                        Klijent k = (Klijent) zahtev.getParametar();
                        Kontroler.getInstance().obrisiKlijenta(k);
                        odgovor.setResponse(null);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    break;
                    case UCITAJ_MESTA:
                        List<Mesto> mesta = kontroler.Kontroler.getInstance().ucitajMesta();
                        odgovor.setResponse(mesta);
                        break;
                    case DODAJ_KLIJENTA:
                        try {
                        Klijent k = (Klijent) zahtev.getParametar();
                        Kontroler.getInstance().dodajKlijenta(k);
                        odgovor.setResponse(null);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    break;
                    case IZMENI_KLIJENTA:
                        try {
                        Klijent k = (Klijent) zahtev.getParametar();
                        Kontroler.getInstance().izmeniKlijenta(k);
                        odgovor.setResponse(null);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    break;
                    case UCITAJ_REZERVACIJE:
                        List<Rezervacija> rezervacije = Kontroler.getInstance().ucitajRezervacije();
                        System.out.println("Obrada Klijentskih Zahteva:" + rezervacije);
                        odgovor.setResponse(rezervacije);
                        break;
                    case DODAJ_REZERVACIJU:
                        try {
                        Rezervacija r = (Rezervacija) zahtev.getParametar();
                        System.out.println("Rezervacija: " + r);
                        Kontroler.getInstance().dodajRezervaciju(r);
                        odgovor.setResponse(null);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    break;
                    case UCITAJ_USLUGE:
                        List<Usluga> usluge = Kontroler.getInstance().ucitajUsluge();
                        odgovor.setResponse(usluge);
                        break;
                    case UCITAJ_FRIZERA:
                        List<Frizer> frizeri = Kontroler.getInstance().ucitajFrizere();
                        odgovor.setResponse(frizeri);
                        break;
                    case IZMENI_REZERVACIJU:
                    try {
                        Rezervacija r = (Rezervacija) zahtev.getParametar();
                        Kontroler.getInstance().izmeniRezervaciju(r);
                        odgovor.setResponse(null);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    break;
                    case OBRISI_REZERVACIJU:
                        try {
                        Rezervacija r = (Rezervacija) zahtev.getParametar();
                        Kontroler.getInstance().obrisiRezervaciju(r);
                        odgovor.setResponse(null);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    break;
                    case DODAJ_USLUGU:
                        try {
                        Usluga u = (Usluga) zahtev.getParametar();
                        System.out.println("OKZ,Usluga: " + u);
                        Kontroler.getInstance().dodajUslugu(u);
                        odgovor.setResponse(null);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    case IZMENI_USLUGU:
                        try {
                        Usluga u = (Usluga) zahtev.getParametar();
                        Kontroler.getInstance().izmeniUslugu(u);
                        odgovor.setResponse(null);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    case OBRISI_USLUGU:
                        try {
                        Usluga u = (Usluga) zahtev.getParametar();
                        Kontroler.getInstance().obrisiUslugu(u);
                        odgovor.setResponse(null);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    case UCITAJ_SERTIFIKATE:
                    try {
                        List<Sertifikat> sertifikati = Kontroler.getInstance().ucitajSertifikate();
                        odgovor.setResponse(sertifikati);
                    } catch (Exception ex) {
                        odgovor.setResponse(ex);
                    }
                    break;
                    default:
                        System.out.println("Ova operacija ne postoji.");
                }
                posiljalac.send(odgovor);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void prekiniNit() {
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }

}
