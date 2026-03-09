/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Frizer;
import domen.Klijent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola Manjencic
 */
public class ModelTabeleKlijent extends AbstractTableModel{

    List<Klijent> listaSvihKlijenata;
    String[] kolone = {"ID","Ime","Prezime","Broj telefona","Email"};

    public ModelTabeleKlijent(List<Klijent> listaSvihKlijenata) {
        this.listaSvihKlijenata = listaSvihKlijenata;
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
        switch(columnIndex){
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
    
    
    
}
