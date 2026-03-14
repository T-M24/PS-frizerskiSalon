/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Frizer;
import domen.Klijent;
import domen.Mesto;
import domen.Rezervacija;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;


public class ModelTabeleKlijent extends AbstractTableModel {

    List<Klijent> originalnaLista;
    List<Klijent> listaSvihKlijenata;
    String[] kolone = {"ID", "Ime", "Prezime", "Broj telefona", "Email", "Mesto"};

    public ModelTabeleKlijent(List<Klijent> listaSvihKlijenata) {
        this.listaSvihKlijenata = listaSvihKlijenata;
        this.originalnaLista = new ArrayList<>(listaSvihKlijenata);
    }

    @Override
    public int getRowCount() {
        return listaSvihKlijenata.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent klijent = listaSvihKlijenata.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return klijent.getIdKlijent();
            case 1:
                return klijent.getIme();
            case 2:
                return klijent.getPrezime();
            case 3:
                return klijent.getBrojTelefona();
            case 4:
                return klijent.getEmail();
            case 5:
                return klijent.getMesto().getNaziv();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Klijent> getListaSvihKlijenata() {
        return listaSvihKlijenata;
    }

    public boolean pretrazi(String ime, String prezime, Mesto mesto) {

        listaSvihKlijenata = originalnaLista.stream()
                .filter(klijent -> (ime == null || ime.isEmpty() || klijent.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(klijent -> (prezime == null || prezime.isEmpty() || klijent.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(klijent -> (mesto == null || klijent.getMesto().getIdMesto() == mesto.getIdMesto()))
                .collect(Collectors.toList());

        fireTableDataChanged();
        return !listaSvihKlijenata.isEmpty();
    }

}
