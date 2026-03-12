/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StavkaRezervacije implements AbstractDomainObject {

    private int rb;
    private String opis;
    private double cena;
    private int kolicina;
    private double iznos;
    private Usluga usluga;
    private Rezervacija rezervacija;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int rb, String opis, double cena, int kolicina, double iznos, Usluga usluga, Rezervacija rezervacija) {
        this.rb = rb;
        this.opis = opis;
        this.cena = cena;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.usluga = usluga;
        this.rezervacija = rezervacija;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    @Override
    public String toString() {
        return "StavkaRezervacije{" + "opis=" + opis + ", cena=" + cena + ", kolicina=" + kolicina + ", iznos=" + iznos + ", usluga=" + usluga + '}';
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
        final StavkaRezervacije other = (StavkaRezervacije) obj;
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        if (Double.doubleToLongBits(this.iznos) != Double.doubleToLongBits(other.iznos)) {
            return false;
        }
        return Objects.equals(this.usluga, other.usluga);
    }

    @Override
    public String getTableName() {
        return "stavkarezervacije";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("rb");
            String opis = rs.getString("opis");
            double cena = rs.getDouble("cena");
            int kolicina = rs.getInt("kolicina");
            double iznos = rs.getDouble("iznos");

            Usluga usluga = new Usluga();
            usluga.setIdUsluga(rs.getInt("usluga.idUsluga"));
            usluga.setNaziv(rs.getString("naziv"));
            usluga.setVremeTrajanja(rs.getInt("vremeTrajanja"));
            usluga.setCena(rs.getDouble("usluga.cena"));

            StavkaRezervacije stavka = new StavkaRezervacije(rb, opis, cena, kolicina, iznos, usluga, null);
            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public String getInsertColumns() {
        return "rb,rezervacija,opis,cena,kolicina,iznos,usluga";
    }

    @Override
    public String getInsertValues() {
        return rb + "," + rezervacija.getIdRezervacija() + ",'" + opis + "'," + cena + "," + kolicina + "," + iznos + "," + usluga.getIdUsluga();
    }

    @Override
    public String getPrimaryKey() {
        return "rb=" + rb + " AND rezervacija=" + rezervacija.getIdRezervacija();
    }

    @Override
    public AbstractDomainObject getObjectFromRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getEditableValues() {
        return "rezervacija=" + rezervacija.getIdRezervacija() + ", opis='" + opis + "', cena=" + cena + ", kolicina=" + kolicina + ", iznos=" + iznos + ", usluga=" + usluga.getIdUsluga();
    }

    public int getVremeTrajanja() {
        return usluga.getVremeTrajanja() * kolicina;
    }
}
