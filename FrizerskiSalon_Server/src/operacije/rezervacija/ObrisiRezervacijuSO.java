/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.rezervacija;

import domen.Rezervacija;
import operacija.ApstraktnaGenerickaOperacija;

public class ObrisiRezervacijuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Rezervacija nije validna!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Rezervacija r = (Rezervacija) param;

        // prvo brisemo sve stavke te rezervacije
        java.sql.Statement st = repozitorijum_db.DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate("DELETE FROM stavkarezervacije WHERE rezervacija=" + r.getIdRezervacija());
        st.close();

        // pa tek onda citavu rezervaciju
        broker.delete(r);
    }
}
