/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesta;

import domen.Mesto;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

public class UcitajMestaSO extends ApstraktnaGenerickaOperacija {

    List<Mesto> mesta;

    public List<Mesto> getMesta() {
        return mesta;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        mesta = broker.getAll(new Mesto(), "");
    }

}
