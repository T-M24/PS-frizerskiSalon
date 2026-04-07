/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesta;

import domen.Mesto;
import operacija.ApstraktnaGenerickaOperacija;

public class DodajMestoSO extends ApstraktnaGenerickaOperacija {
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Mesto)) {
            throw new Exception("Mesto nije validno!");
        }
    }
    
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Mesto) param);
    }
    
}
