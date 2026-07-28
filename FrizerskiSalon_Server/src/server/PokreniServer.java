package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

public class PokreniServer extends Thread {

    private ServerSocket serverSocket;
    private ObradaKlijentskihZahteva okz;
    private boolean kraj = false;
    private boolean pokrenut = false;

    public PokreniServer() {
    }

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
                System.out.println("Klijent je povezan!");
                okz = new ObradaKlijentskihZahteva(s);
                okz.start();
            }
        } catch (java.net.BindException ex) {
            System.out.println("Port je već zauzet, server se ne može pokrenuti!");
        } catch (SocketException ex) {
            if (!kraj) {
                Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pokrenut = false;
        }
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            if (okz != null) {
                okz.prekiniNit();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}