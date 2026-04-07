/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domen.Klijent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

public class PokreniServer extends Thread {

    ServerSocket serverSocket;
    boolean kraj = false;
    List<ObradaKlijentskihZahteva> listaKlijenata;

    public PokreniServer() {
        listaKlijenata = new ArrayList<>();
    }
    private boolean pokrenut = false;

    public boolean isPokrenut() {
        return pokrenut;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            pokrenut = true;
            while (!kraj) {
                Socket s = serverSocket.accept();
                listaKlijenata.removeIf(okz -> !okz.isAlive());
                if (!listaKlijenata.isEmpty()) {
                    System.out.println("Klijent je već povezan, odbijam konekciju!");
                    s.close();
                    continue;
                }
                System.out.println("Klijent je povezan!");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                listaKlijenata.add(okz);
                okz.start();
            }
        } catch (java.net.BindException ex) {
            System.out.println("Port je već zauzet, server se ne može pokrenuti!");
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva okz : listaKlijenata) {
                okz.prekiniNit();
            }
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
