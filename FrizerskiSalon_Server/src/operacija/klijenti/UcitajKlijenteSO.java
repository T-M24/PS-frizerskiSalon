/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klijenti;

import domen.Klijent;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nikola Manjencic
 */
public class UcitajKlijenteSO extends ApstraktnaGenerickaOperacija{

    List<Klijent> klijenti;

    public List<Klijent> getKlijenti() {
        return klijenti;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        klijenti = broker.getAll(new Klijent(), "");
    }
    
}
