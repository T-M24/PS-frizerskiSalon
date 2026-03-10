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

public class Usluga implements AbstractDomainObject {

    private int idUsluga;
    private String naziv;
    private int vremeTrajanja;
    private double cena;

    public Usluga() {
    }

    public Usluga(int idUsluga, String naziv, int vremeTrajanja, double cena) {
        this.idUsluga = idUsluga;
        this.naziv = naziv;
        this.vremeTrajanja = vremeTrajanja;
        this.cena = cena;
    }

    public int getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(int idUsluga) {
        this.idUsluga = idUsluga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVremeTrajanja() {
        return vremeTrajanja;
    }

    public void setVremeTrajanja(int vremeTrajanja) {
        this.vremeTrajanja = vremeTrajanja;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Usluga other = (Usluga) obj;
        if (this.vremeTrajanja != other.vremeTrajanja) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return naziv + ", " + cena;
    }

    @Override
    public String getTableName() {
        return "usluga";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            int idUsluga = rs.getInt("idUsluga");
            String naziv = rs.getString("naziv");
            int vremeTrajanja = rs.getInt("vremeTrajanja");
            double cena = rs.getDouble("cena");
            Usluga usluga = new Usluga(idUsluga, naziv, vremeTrajanja, cena);
            lista.add(usluga);
        }
        return lista;
    }

    @Override
    public String getInsertColumns() {
        return "naziv, vremeTrajanja, cena";
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getPrimaryKey() {
        return "usluga.idUsluga=" + idUsluga;
    }

    @Override
    public AbstractDomainObject getObjectFromRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getEditableValues() {
        return "naziv='" + naziv + "', vremeTrajanja=" + vremeTrajanja + ", cena=" + cena;
    }

}
