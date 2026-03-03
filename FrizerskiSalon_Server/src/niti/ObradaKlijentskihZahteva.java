/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

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

/**
 *
 * @author Nikola Manjencic
 */
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
            Zahtev zahtev = primalac.primi();
            Odgovor odgovor = new Odgovor();
            switch (zahtev.getOperacija()) {
                //case val:
                //

                default:
                    System.out.println("Ova operacija ne postoji.");
            }
            posiljalac.posalji(odgovor);
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
