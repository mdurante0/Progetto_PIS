package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CategoriaTableModel extends AbstractTableModel {

    private List<RigaCategoriaProdotto> righe = new ArrayList<RigaCategoriaProdotto>();

    public List<RigaCategoriaProdotto> getRighe() {
        return righe;
    }

    public CategoriaTableModel(List<RigaCategoriaProdotto> righe) {
        this.righe = righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaCategoriaProdotto riga = righe.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return riga.getNomeCategoria();
            case 1:
                return riga.getNomeSopraCategoria();
            case 2:
                return riga.getModificaButton();
            case 3:
                return riga.getEliminaButton();

        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaCategoriaProdotto riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0: riga.setNomeCategoria(value.toString());
            case 1: riga.setNomeSopraCategoria(value.toString());
            case 2: riga.setModificaButton(new JButton());
            case 3: riga.setEliminaButton(new JButton());

        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Categoria";
            case 1: return "Sottocategoria di";
            case 2: return "Modifica";
            case 3: return "Elimina";

        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 2) return JButton.class;
        if(columnIndex == 3) return JButton.class;
        return Object.class;
    }
}
