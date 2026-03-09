/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klijenti;

import domen.Klijent;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Nikola Manjencic
 */
public class IzmeniKlijentaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Klijent)){
            throw new Exception("Sistem ne moze da izmeni klijenta!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Klijent) param);
    }
    
}
