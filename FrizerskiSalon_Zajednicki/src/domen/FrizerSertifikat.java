/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class FrizerSertifikat implements AbstractDomainObject {

    private LocalDateTime datumIzdavanja;
    private Frizer frizer;
    private Sertifikat sertifikat;

    public FrizerSertifikat() {
    }

    public FrizerSertifikat(LocalDateTime datumIzdavanja, Frizer frizer, Sertifikat sertifikat) {
        this.datumIzdavanja = datumIzdavanja;
        this.frizer = frizer;
        this.sertifikat = sertifikat;
    }

    public LocalDateTime getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDateTime datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Frizer getFrizer() {
        return frizer;
    }

    public void setFrizer(Frizer frizer) {
        this.frizer = frizer;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
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
        final FrizerSertifikat other = (FrizerSertifikat) obj;
        return Objects.equals(this.datumIzdavanja, other.datumIzdavanja);
    }

    @Override
    public String toString() {
        return "FrizerSertifikat{" + "datumIzdavanja=" + datumIzdavanja + ", frizer=" + frizer + ", sertifikat=" + sertifikat + '}';
    }

    @Override
    public String getTableName() {
        return "frizersertifikat";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInsertColumns() {
        return "frizer,sertifikat,datumIzdavanja";
    }

    @Override
    public String getInsertValues() {
        return frizer.getIdFrizer() + "," + sertifikat.getIdSertifikat() + ",'" + datumIzdavanja + "'";
    }

    @Override
    public String getPrimaryKey() {
        return "frizer=" + frizer.getIdFrizer() + " AND sertifikat=" + sertifikat.getIdSertifikat();
    }

    @Override
    public AbstractDomainObject getObjectFromRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getEditableValues() {
        return "frizer=" + frizer.getIdFrizer() + ", sertifikat=" + sertifikat.getIdSertifikat() + ", datumIzdavanja='" + datumIzdavanja + "'";
    }
}
