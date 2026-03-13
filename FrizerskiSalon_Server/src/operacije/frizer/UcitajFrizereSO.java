/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.frizer;

import domen.Frizer;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

public class UcitajFrizereSO extends ApstraktnaGenerickaOperacija{

    List<Frizer> frizeri;

    public List<Frizer> getFrizeri() {
        return frizeri;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        frizeri = broker.getAll(new Frizer(),
            " JOIN frizersertifikat ON frizer.idFrizer = frizersertifikat.frizer"
            + " JOIN sertifikat ON frizersertifikat.sertifikat = sertifikat.idSertifikat"
            + " ORDER BY frizer.idFrizer");
    }
    
}
