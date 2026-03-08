/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Frizer;
import java.io.IOException;
import java.net.Socket;

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
        } catch(IOException ex){
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

}
