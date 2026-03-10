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

public class Sertifikat implements AbstractDomainObject {

    private int idSertifikat;
    private String naziv;
    private String institucija;

    public Sertifikat() {
    }

    public Sertifikat(int idSertifikat, String naziv, String institucija) {
        this.idSertifikat = idSertifikat;
        this.naziv = naziv;
        this.institucija = institucija;
    }

    public int getIdSertifikat() {
        return idSertifikat;
    }

    public void setIdSertifikat(int idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    @Override
    public String toString() {
        return naziv + ", " + institucija;
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
        final Sertifikat other = (Sertifikat) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.institucija, other.institucija);
    }

    @Override
    public String getTableName() {
        return "sertifikat";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            int idSertifikat = rs.getInt("idSertifikat");
            String naziv = rs.getString("naziv");
            String institucija = rs.getString("institucija");
            Sertifikat sertifikat = new Sertifikat(idSertifikat, naziv, institucija);
            lista.add(sertifikat);
        }
        return lista;
    }

    @Override
    public String getInsertColumns() {
        return "naziv, institucija";
    }

    @Override
    public String getInsertValues() {
        return "'" + naziv + "','" + institucija + "'";
    }

    @Override
    public String getPrimaryKey() {
        return "sertifikat.idSertifikat=" + idSertifikat;
    }

    @Override
    public AbstractDomainObject getObjectFromRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getEditableValues() {
        return "naziv='" + naziv + "', institucija='" + institucija + "'";
    }

}
