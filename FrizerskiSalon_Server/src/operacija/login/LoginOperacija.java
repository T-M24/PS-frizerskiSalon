/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import domen.Frizer;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;
import repozitorijum.Repozitorijum;

public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    public LoginOperacija() {
    }

    Frizer frizer;

    @Override
    protected void preduslovi(Object param) throws Exception {
    if(param == null || !(param instanceof Frizer)){
           throw new Exception("Sistem ne moze da pronađe frizera!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Frizer> listaSvihFrizera = broker.getAll((Frizer) param, null);
        System.out.println("LoginOperacija: " + listaSvihFrizera);
        if (listaSvihFrizera.contains((Frizer) param)) {
            for (Frizer f : listaSvihFrizera) {
                if (f.equals((Frizer) param)) {
                    frizer = f;
                    return;
                }
            }
        } else {
            frizer = null;
        }
    }

    public Frizer getFrizer() {
        return frizer;
    }

}
