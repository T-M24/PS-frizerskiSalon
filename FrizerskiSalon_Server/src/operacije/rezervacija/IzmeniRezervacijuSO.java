package operacije.rezervacija;

import domen.Rezervacija;
import domen.StavkaRezervacije;
import operacija.ApstraktnaGenerickaOperacija;

public class IzmeniRezervacijuSO extends ApstraktnaGenerickaOperacija {

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
        r.izracunajUkupnoVreme();
        broker.edit(r);

        // obrisi SVE stavke te rezervacije!!!!!!!!!
        java.sql.Statement st = repozitorijum_db.DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate("DELETE FROM stavkarezervacije WHERE rezervacija=" + r.getIdRezervacija());
        st.close();

        // ubaci stavke koje su ostale
        int rb = 1;
        for (StavkaRezervacije stavka : r.getStavke()) {
            stavka.setRb(rb++);
            stavka.setRezervacija(r);
            broker.add(stavka);
        }
    }
}
