/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Frizer;
import komunikacija.Zahtev;
import operacija.login.LoginOperacija;

/**
 *
 * @author Nikola Manjencic
 */
public class Kontroler {
    private static Kontroler instance;
    public static Kontroler getInstance(){
        if(instance == null){
            instance = new Kontroler();
        }
        return instance;
    }
    
    private Kontroler(){
        
    }

    public Frizer login(Frizer frizer) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(frizer, null); 
        System.out.println("Kontroler: "+ operacija.getFrizer());
        return operacija.getFrizer();
    }
}
