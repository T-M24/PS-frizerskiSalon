/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.DodajUsluguForma;

public class DodajUsluguKontroler {
    
    private final DodajUsluguForma duf;

    public DodajUsluguKontroler(DodajUsluguForma duf) {
        this.duf = duf;
        addActionListeners();
    }

    private void addActionListeners() {
        //implementacija za dugmice
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        duf.setVisible(true);
    }

    private void pripremiFormu() {
        
    }
    
}
