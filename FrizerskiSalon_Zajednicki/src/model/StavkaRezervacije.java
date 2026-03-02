/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author Nikola Manjencic
 */
public class StavkaRezervacije {
    private int rb;
    private String opis;
    private double cena;
    private int kolicina;
    private double iznos;
    private Usluga usluga;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int rb, String opis, double cena, int kolicina, double iznos, Usluga usluga) {
        this.rb = rb;
        this.opis = opis;
        this.cena = cena;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.usluga = usluga;
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
    
    
}
