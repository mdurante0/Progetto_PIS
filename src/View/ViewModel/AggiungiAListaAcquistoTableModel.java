package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AggiungiAListaAcquistoTableModel extends AbstractTableModel {

    private List<RigaListaAcquisto> righe = new ArrayList<RigaListaAcquisto>();

    public List<RigaListaAcquisto> getRighe() {
        return righe;
    }

    public AggiungiAListaAcquistoTableModel(List<RigaListaAcquisto> righe) {
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

        RigaListaAcquisto riga = righe.get(rowIndex);
        switch (columnIndex) {
            case 0: return riga.getNomeLista();
            case 1: return riga.getCostoTotale();
            case 2: return riga.getAggiungiButton();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaListaAcquisto riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: riga.setNomeLista(value.toString());
            case 1: riga.setCostoTotale(Float.parseFloat(value.toString()));
            case 2: riga.setAggiungiButton(new JButton());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Nome lista";
            case 1: return "Costo totale";
            case 2: return "Aggiungi";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 2 ) return JButton.class;

        return Object.class;
    }
}
