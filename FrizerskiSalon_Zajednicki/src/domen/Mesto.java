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

public class Mesto implements AbstractDomainObject{

    private int idMesto;
    private String naziv;
    private String postanskiBroj;

    public Mesto() {
    }

    public Mesto(int idMesto, String naziv, String postanskiBroj) {
        this.idMesto = idMesto;
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return naziv;
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
        final Mesto other = (Mesto) obj;
        return Objects.equals(this.postanskiBroj, other.postanskiBroj);
    }

    @Override
    public String getTableName() {
        return "mesto";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            int idMesto = rs.getInt("idMesto");
            String naziv = rs.getString("naziv");
            String postanskiBroj = rs.getString("postanskiBroj");
            Mesto mesto = new Mesto(idMesto, naziv, postanskiBroj);
            lista.add(mesto);
        }
        return lista;
    }

    @Override
    public String getInsertColumns() {
        return "naziv, postanskiBroj";
    }

    @Override
    public String getInsertValues() {
        return "'" + naziv + "','" + postanskiBroj + "'";
    }

    @Override
    public String getPrimaryKey() {
        return "mesto.idMesto=" + idMesto;
    }

    @Override
    public AbstractDomainObject getObjectFromRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getEditableValues() {
        return "naziv='" + naziv + "', postanskiBroj='" + postanskiBroj + "'";
    }

}
