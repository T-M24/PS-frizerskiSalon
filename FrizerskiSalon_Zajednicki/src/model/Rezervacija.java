/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.Objects;


public class Rezervacija {
    private int idRezervacija;
    private LocalDateTime datumRezervacije;
    private int ukupnoVremeTrajanja;
    private double ukupanIznos;

    public Rezervacija() {
    }

    public Rezervacija(int idRezervacija, LocalDateTime datumRezervacije, int ukupnoVremeTrajanja, double ukupanIznos) {
        this.idRezervacija = idRezervacija;
        this.datumRezervacije = datumRezervacije;
        this.ukupnoVremeTrajanja = ukupnoVremeTrajanja;
        this.ukupanIznos = ukupanIznos;
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
        final Rezervacija other = (Rezervacija) obj;
        return Objects.equals(this.datumRezervacije, other.datumRezervacije);
    }

    @Override
    public String toString() {
        return datumRezervacije + ": " + ukupnoVremeTrajanja+ ", cena: "+ukupanIznos;
    }
    
}
