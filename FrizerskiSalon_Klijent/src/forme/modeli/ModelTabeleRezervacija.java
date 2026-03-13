package forme.modeli;

import domen.Frizer;
import domen.Klijent;
import domen.Rezervacija;
import domen.Usluga;
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
                return r.getDatumRezervacije().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
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

    public boolean pretrazi(Frizer frizer, Klijent klijent, Usluga usluga) {
        listaRezervacija = originalnaLista.stream()
                .filter(r -> {
                    // filter po frizeru
                    if (frizer != null && r.getFrizer().getIdFrizer() != frizer.getIdFrizer()) {
                        return false;
                    }
                    // filter po klijentu
                    if (klijent != null && r.getKlijent().getIdKlijent() != klijent.getIdKlijent()) {
                        return false;
                    }
                    // filter po usluzi - proveravamo stavke rezervacije
                    if (usluga != null) {
                        boolean imaUslugu = r.getStavke().stream()
                                .anyMatch(s -> s.getUsluga().getIdUsluga() == usluga.getIdUsluga());
                        if (!imaUslugu) {
                            return false;
                        }
                    }
                    return true;
                })
                .collect(java.util.stream.Collectors.toList());

        fireTableDataChanged();
        return !listaRezervacija.isEmpty();
    }
}
