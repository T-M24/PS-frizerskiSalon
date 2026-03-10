/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.usluga;

import domen.Usluga;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nikola Manjencic
 */
public class UcitajUslugeSO extends ApstraktnaGenerickaOperacija{

    List<Usluga> usluge;

    public List<Usluga> getUsluge() {
        return usluge;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        usluge = broker.getAll(new Usluga(), "");
    }
    
}
