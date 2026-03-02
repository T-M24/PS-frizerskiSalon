/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Nikola Manjencic
 */
public class FrizerSertifikat {
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
    
}
