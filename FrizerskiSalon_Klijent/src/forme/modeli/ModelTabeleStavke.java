/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.StavkaRezervacije;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola Manjencic
 */
public class ModelTabeleStavke extends AbstractTableModel {

    List<StavkaRezervacije> listaStavki;
    String[] kolone = {"RB", "Usluga", "Opis", "Cena", "Kolicina", "Iznos"};

    public ModelTabeleStavke(List<StavkaRezervacije> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public List<StavkaRezervacije> getListaStavki() {
        return listaStavki;
    }
 
    @Override
    public int getRowCount() {
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRezervacije s = listaStavki.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getRb();
            case 1:
                return s.getUsluga().getNaziv();
            case 2:
                return s.getOpis();
            case 3:
                return s.getCena();
            case 4:
                return s.getKolicina();
            case 5:
                return s.getIznos();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajStavku(StavkaRezervacije stavka) {
        listaStavki.add(stavka);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        listaStavki.remove(row);
        fireTableDataChanged();
    }
}
