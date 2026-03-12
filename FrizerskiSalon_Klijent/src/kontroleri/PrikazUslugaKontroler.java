/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Usluga;
import forme.PrikazUslugaForma;
import forme.modeli.ModelTabeleUsluge;
import java.util.List;


public class PrikazUslugaKontroler {
    private final PrikazUslugaForma puf;

    public PrikazUslugaKontroler(PrikazUslugaForma puf) {
        this.puf = puf;
        addActionListener();
    }

    private void addActionListener() {
        //implementacija za dugmice posle!
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        puf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Usluga> usluge = komunikacija.Komunikacija.getInstance().ucitajUsluge();
        ModelTabeleUsluge mtu = new ModelTabeleUsluge(usluge);
        puf.getjTableUsluga().setModel(mtu);
    }
    
}
