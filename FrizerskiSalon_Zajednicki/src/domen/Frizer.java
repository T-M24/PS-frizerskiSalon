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

public class Frizer implements AbstractDomainObject {

    private int idFrizer;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;

    public Frizer() {
    }

    public Frizer(int idFrizer, String ime, String prezime, String korisnickoIme, String sifra) {
        this.idFrizer = idFrizer;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdFrizer() {
        return idFrizer;
    }

    public void setIdFrizer(int idFrizer) {
        this.idFrizer = idFrizer;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Frizer other = (Frizer) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String getTableName() {
        return "frizer";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idFrizer");
            boolean postoji = lista.stream()
                    .anyMatch(o -> ((Frizer) o).getIdFrizer() == id);
            if (postoji) {
                continue; //preskakanje duplikata, morala sam zbog pretrage rez
            }
            Frizer f = new Frizer();
            f.setIdFrizer(id);
            f.setIme(rs.getString("ime"));
            f.setPrezime(rs.getString("prezime"));
            f.setKorisnickoIme(rs.getString("korisnickoIme"));
            f.setSifra(rs.getString("sifra"));
            lista.add(f);
        }
        return lista;
    }

    @Override
    public String getInsertColumns() {
        return "ime,prezime,korisnickoIme,sifra";
    }

    @Override
    public String getInsertValues() {
        return "'" + ime + "','" + prezime + "','" + korisnickoIme + "','" + sifra + "'";
    }

    @Override
    public String getPrimaryKey() {
        return "frizer.idFrizer=" + idFrizer;
    }

    @Override
    public AbstractDomainObject getObjectFromRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getEditableValues() {
        return "ime='" + ime + "', prezime='" + prezime + "', korisnickoIme='" + korisnickoIme + "', sifra='" + sifra + "'";
    }

}
