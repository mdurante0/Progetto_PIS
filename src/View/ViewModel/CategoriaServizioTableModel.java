package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CategoriaServizioTableModel extends AbstractTableModel {

    private List<RigaCategoria> righe = new ArrayList<>();

    public List<RigaCategoria> getRighe() {
        return righe;
    }

    public CategoriaServizioTableModel(List<RigaCategoria> righe) {
        this.righe = righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RigaCategoria riga = righe.get(rowIndex);
        switch (columnIndex) {
            case 0: return riga.getNomeCategoria();
            case 1: return riga.getModificaButton();
            case 2: return riga.getEliminaButton();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaCategoria riga = righe.get(rowIndex);
        switch(columnIndex) {
            case 0: riga.setNomeCategoria(value.toString());
            case 1: riga.setModificaButton(new JButton());
            case 2: riga.setEliminaButton(new JButton());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: return "Categoria";
            case 1: return "Modifica";
            case 2: return "Elimina";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 1) return JButton.class;
        if(columnIndex == 2) return JButton.class;
        return Object.class;
    }
}