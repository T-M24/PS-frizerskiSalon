/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Rezervacija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleRezervacija extends AbstractTableModel {

    List<Rezervacija> listaRezervacija;
    String[] kolone = {"ID", "Frizer", "Klijent", "Datum", "Vreme trajanja", "Iznos"};

    public ModelTabeleRezervacija(List<Rezervacija> listaRezervacija) {
        this.listaRezervacija = listaRezervacija;
    }

    public List<Rezervacija> getListaRezervacija() {
        return listaRezervacija;
    }

    @Override
    public int getRowCount() {
        return listaRezervacija.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija r = listaRezervacija.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getIdRezervacija();
            case 1:
                return r.getFrizer().getIme() + " " + r.getFrizer().getPrezime();
            case 2:
                return r.getKlijent().getIme() + " " + r.getKlijent().getPrezime();
            case 3:
                return r.getDatumRezervacije();
            case 4:
                return r.getUkupnoVremeTrajanja();
            case 5:
                return r.getUkupanIznos();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
}
