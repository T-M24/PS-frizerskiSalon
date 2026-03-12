/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.rezervacija;

import domen.Rezervacija;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

public class UcitajRezervacijeSO extends ApstraktnaGenerickaOperacija {

    List<Rezervacija> rezervacije;

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        rezervacije = broker.getAll(new Rezervacija(),
                " JOIN frizer ON rezervacija.frizer = frizer.idFrizer"
                + " JOIN klijent ON rezervacija.klijent = klijent.idKlijent"
                + " JOIN mesto ON klijent.mesto = mesto.idMesto"
                + " JOIN stavkarezervacije ON rezervacija.idRezervacija = stavkarezervacije.rezervacija"
                + " JOIN usluga ON stavkarezervacije.usluga = usluga.idUsluga"
                + " ORDER BY rezervacija.idRezervacija"); // ← DODAJ OVO
    }

}
