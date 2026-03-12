package forme.modeli;

import domen.Rezervacija;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleRezervacija extends AbstractTableModel {

    List<Rezervacija> originalnaLista;
    List<Rezervacija> listaRezervacija;
    String[] kolone = {"ID", "Frizer", "Klijent", "Datum", "Vreme trajanja", "Iznos"};

    public ModelTabeleRezervacija(List<Rezervacija> listaRezervacija) {
        this.listaRezervacija = listaRezervacija;
        this.originalnaLista = new ArrayList<>(listaRezervacija);
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

    public void pretrazi(String imeFrizera) {
        if (imeFrizera == null || imeFrizera.isEmpty()) {
            listaRezervacija = new java.util.ArrayList<>(originalnaLista);
        } else {
            listaRezervacija = originalnaLista.stream()
                    .filter(r -> (r.getFrizer().getIme() + " " + r.getFrizer().getPrezime())
                    .toLowerCase().contains(imeFrizera.toLowerCase()))
                    .collect(java.util.stream.Collectors.toList());
        }
        fireTableDataChanged();
    }
}
