package forme.modeli;

import domen.Sertifikat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleSertifikat extends AbstractTableModel {

    private List<Sertifikat> originalnaLista;
    private List<Sertifikat> listaSertifikata;
    private String[] kolone = {"Naziv", "Institucija"};

    public ModelTabeleSertifikat(List<Sertifikat> listaSertifikata) {
        this.listaSertifikata = listaSertifikata;
        this.originalnaLista = new ArrayList<>(listaSertifikata);
    }

    @Override
    public int getRowCount() {
        return listaSertifikata.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sertifikat s = listaSertifikata.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getNaziv();
            case 1:
                return s.getInstitucija();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void pretrazi(String naziv, String institucija) {
        listaSertifikata = originalnaLista.stream()
                .filter(s -> (naziv == null || naziv.isEmpty()
                || s.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                && (institucija == null || institucija.isEmpty()
                || s.getInstitucija().toLowerCase().contains(institucija.toLowerCase())))
                .collect(java.util.stream.Collectors.toList());
        fireTableDataChanged();
    }

    public List<Sertifikat> getListaSertifikata() {
        return listaSertifikata;
    }
}
