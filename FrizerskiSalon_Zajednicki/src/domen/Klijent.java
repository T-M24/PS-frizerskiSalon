/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Klijent implements AbstractDomainObject {

    private int idKlijent;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String email;
    private Mesto mesto;

    public Klijent() {
    }

    public Klijent(int idKlijent, String ime, String prezime, String brojTelefona, String email, Mesto mesto) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.mesto = mesto;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
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

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefona, other.brojTelefona)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String getTableName() {
        return "klijent";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            int idKlijent = rs.getInt("idKlijent");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String brojTelefona = rs.getString("brojTelefona");
            String email = rs.getString("email");
            int idMesto = rs.getInt("mesto");
            String nazivMesta = rs.getString("naziv");
            String postanskiBroj = rs.getString("postanskiBroj");
            Mesto mesto = new Mesto();
            mesto.setIdMesto(idMesto);
            mesto.setNaziv(nazivMesta);
            mesto.setPostanskiBroj(postanskiBroj);
            Klijent kl = new Klijent(idKlijent, ime, prezime, brojTelefona, email, mesto);
            lista.add(kl);
        }

        return lista;
    }

    @Override
    public String getInsertColumns() {
        return "ime, prezime, brojTelefona,email,mesto";
    }

    @Override
    public String getInsertValues() {
        return "'" + ime + "','" + prezime + "','" + brojTelefona + "','" + email + "'," + mesto.getIdMesto();
    }

    @Override
    public String getPrimaryKey() {
        return "klijent.idKlijent=" + idKlijent;
    }

    @Override
    public AbstractDomainObject getObjectFromRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getEditableValues() {
        return "ime='" + ime + "', prezime='" + prezime + "', brojTelefona='" + brojTelefona + "', email='" + email + "', mesto=" + mesto.getIdMesto();
    }

}
