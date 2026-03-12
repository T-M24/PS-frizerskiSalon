/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.usluga;

import domen.Usluga;
import operacija.ApstraktnaGenerickaOperacija;

public class ObrisiUsluguSO extends ApstraktnaGenerickaOperacija{



    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Usluga)){
           throw new Exception("Sistem ne moze da obriše uslugu!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Usluga) param);
    }

}
