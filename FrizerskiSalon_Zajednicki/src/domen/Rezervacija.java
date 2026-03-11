/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rezervacija implements AbstractDomainObject {

    private List<StavkaRezervacije> stavke = new ArrayList<>();
    private Frizer frizer;
    private Klijent klijent;
    private int idRezervacija;
    private LocalDateTime datumRezervacije;
    private int ukupnoVremeTrajanja;
    private double ukupanIznos;

    public Rezervacija() {
    }

    public Rezervacija(Frizer frizer, Klijent klijent, int idRezervacija, LocalDateTime datumRezervacije, int ukupnoVremeTrajanja, double ukupanIznos) {
        this.frizer = frizer;
        this.klijent = klijent;
        this.idRezervacija = idRezervacija;
        this.datumRezervacije = datumRezervacije;
        this.ukupnoVremeTrajanja = ukupnoVremeTrajanja;
        this.ukupanIznos = ukupanIznos;
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

    public Frizer getFrizer() {
        return frizer;
    }

    public void setFrizer(Frizer frizer) {
        this.frizer = frizer;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public LocalDateTime getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(LocalDateTime datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public int getUkupnoVremeTrajanja() {
        return ukupnoVremeTrajanja;
    }

    public void setUkupnoVremeTrajanja(int ukupnoVremeTrajanja) {
        this.ukupnoVremeTrajanja = ukupnoVremeTrajanja;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    @Override
    public String toString() {
        return frizer.getIme() + " " + frizer.getPrezime() + "( " + datumRezervacije + " )";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rezervacija other = (Rezervacija) obj;
        if (!Objects.equals(this.frizer, other.frizer)) {
            return false;
        }
        if (!Objects.equals(this.klijent, other.klijent)) {
            return false;
        }
        return Objects.equals(this.datumRezervacije, other.datumRezervacije);
    }

    @Override
    public String getTableName() {
        return "rezervacija";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        Rezervacija trenutna = null;

        while (rs.next()) {
            int idRez = rs.getInt("idRezervacija");

            if (trenutna == null || trenutna.getIdRezervacija() != idRez) {
                LocalDateTime datum = rs.getTimestamp("datumRezervacije").toLocalDateTime();
                int ukupnoVreme = rs.getInt("ukupnoVremeTrajanja");
                double ukupanIznos = rs.getDouble("ukupanIznos");

                Frizer frizer = new Frizer();
                frizer.setIdFrizer(rs.getInt("rezervacija.frizer"));
                frizer.setIme(rs.getString("frizer.ime"));
                frizer.setPrezime(rs.getString("frizer.prezime"));

                Klijent klijent = new Klijent();
                klijent.setIdKlijent(rs.getInt("rezervacija.klijent"));
                klijent.setIme(rs.getString("klijent.ime"));
                klijent.setPrezime(rs.getString("klijent.prezime"));
                klijent.setBrojTelefona(rs.getString("brojTelefona"));
                klijent.setEmail(rs.getString("email"));

                Mesto mesto = new Mesto();
                mesto.setIdMesto(rs.getInt("klijent.mesto"));
                mesto.setNaziv(rs.getString("naziv"));
                klijent.setMesto(mesto);

                trenutna = new Rezervacija(frizer, klijent, idRez, datum, ukupnoVreme, ukupanIznos);
                lista.add(trenutna);
            }

            Usluga usluga = new Usluga();
            usluga.setIdUsluga(rs.getInt("usluga.idUsluga"));
            usluga.setNaziv(rs.getString("usluga.naziv"));
            usluga.setCena(rs.getDouble("usluga.cena"));
            usluga.setVremeTrajanja(rs.getInt("vremeTrajanja"));

            StavkaRezervacije stavka = new StavkaRezervacije(
                    rs.getInt("rb"),
                    rs.getString("opis"),
                    rs.getDouble("stavkarezervacije.cena"),
                    rs.getInt("kolicina"),
                    rs.getDouble("iznos"),
                    usluga,
                    trenutna
            );
            trenutna.getStavke().add(stavka);
        }
        return lista;
    }

    @Override
    public String getInsertColumns() {
        return "datumRezervacije,ukupanIznos, ukupnoVremeTrajanja,frizer,klijent";
    }

    @Override
    public String getInsertValues() {
        return "'" + datumRezervacije + "'," + ukupanIznos + "," + ukupnoVremeTrajanja + "," + frizer.getIdFrizer() + "," + klijent.getIdKlijent();
    }

    @Override
    public String getPrimaryKey() {
        return "rezervacija.idRezervacija=" + idRezervacija;
    }

    @Override
    public AbstractDomainObject getObjectFromRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getEditableValues() {
        return "datumRezervacije='" + datumRezervacije + "', ukupanIznos=" + ukupanIznos + ", ukupnoVremeTrajanja=" + ukupnoVremeTrajanja + ", frizer=" + frizer.getIdFrizer() + ", klijent=" + klijent.getIdKlijent();
    }

    public void izracunajUkupnoVreme() {
        int ukupnoVreme = 0;
        double ukupnoIznos = 0;

        for (StavkaRezervacije stavka : stavke) {
            ukupnoVreme += stavka.getUsluga().getVremeTrajanja() * stavka.getKolicina();
            ukupnoIznos += stavka.getIznos();
        }

        this.ukupnoVremeTrajanja = ukupnoVreme;
        this.ukupanIznos = ukupnoIznos;
    }
}
