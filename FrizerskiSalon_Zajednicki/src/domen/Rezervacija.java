/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rezervacija {
    
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
        return "Rezervacija{" + "frizer=" + frizer + ", klijent=" + klijent + ", datumRezervacije=" + datumRezervacije + '}';
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
    
}
