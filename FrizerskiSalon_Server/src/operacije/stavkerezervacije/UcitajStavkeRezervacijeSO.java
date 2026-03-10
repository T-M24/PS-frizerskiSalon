/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.stavkerezervacije;

import domen.Rezervacija;
import domen.StavkaRezervacije;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nikola Manjencic
 */
public class UcitajStavkeRezervacijeSO extends ApstraktnaGenerickaOperacija {

    List<StavkaRezervacije> listaStavki;

    public List<StavkaRezervacije> getListaStavki() {
        return listaStavki;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Sistem ne moze da vrati stavku, promeniti ovaj exc posle");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Rezervacija r = (Rezervacija) param;
        listaStavki = broker.getAll(new StavkaRezervacije(), " JOIN usluga ON stavkarezervacije.usluga=usluga.idUsluga"
                + " WHERE stavkarezervacije.rezervacija = " + r.getIdRezervacija());
    }

}
