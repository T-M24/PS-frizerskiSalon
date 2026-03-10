/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.rezervacija;

import domen.Rezervacija;
import domen.StavkaRezervacije;
import operacija.ApstraktnaGenerickaOperacija;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import repozitorijum_db.DbConnectionFactory;

public class DodajRezervacijuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Rezervacija nije validna!");
        }
        Rezervacija r = (Rezervacija) param;
        if (r.getStavke() == null || r.getStavke().isEmpty()) {
            throw new Exception("Rezervacija mora imati bar jednu stavku!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Rezervacija r = (Rezervacija) param;

        broker.add(r.getKlijent());

        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() as id");
        if (rs.next()) {
            r.getKlijent().setIdKlijent(rs.getInt("id"));
        }
        rs.close();
        st.close();

        broker.add(r);

        
        //uzimam last insert id
        st = DbConnectionFactory.getInstance().getConnection().createStatement();
        rs = st.executeQuery("SELECT LAST_INSERT_ID() as id");
        if (rs.next()) {
            r.setIdRezervacija(rs.getInt("id"));
        }
        rs.close();
        st.close();

        for (StavkaRezervacije stavka : r.getStavke()) {
            stavka.setRezervacija(r);
            broker.add(stavka);
        }
    }

}
