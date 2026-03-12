/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Usluga;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola Manjencic
 */
public class ModelTabeleUsluge extends AbstractTableModel {

    List<Usluga> listaSvihUsluga;
    List<Usluga> originalnaLista;
    String[] kolone = {"ID", "Naziv", "Vreme trajanja", "Cena"};

    @Override
    public int getRowCount() {
        return listaSvihUsluga.size();
    }

    public ModelTabeleUsluge(List<Usluga> listaSvihUsluga) {
        this.listaSvihUsluga = listaSvihUsluga;
    }

    public List<Usluga> getListaSvihUsluga() {
        return listaSvihUsluga;
    }
    
    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usluga u = listaSvihUsluga.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getIdUsluga();
            case 1:
                return u.getNaziv();
            case 2:
                return u.getVremeTrajanja();
            case 3:
                return u.getCena();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    

}
