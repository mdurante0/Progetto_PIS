package View.ViewModel;

import Model.Cliente;
import Model.Utente;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DettagliListaAcquistoTableModel extends AbstractTableModel {

    private List<RigaDettagliListaAcquisto> righe = new ArrayList<RigaDettagliListaAcquisto>();

    public List<RigaDettagliListaAcquisto> getRighe() {
        return righe;
    }

    public DettagliListaAcquistoTableModel(List<RigaDettagliListaAcquisto> righe) {
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

        RigaDettagliListaAcquisto riga = righe.get(rowIndex);


        switch (columnIndex) {

            case 0: return riga.getNomeProdotto();
            case 1: return riga.getQuantita();
            case 2: return riga.getCosto();
            case 3: return riga.getEliminaButton();

        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaDettagliListaAcquisto riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0: riga.setNomeProdotto(value.toString());
            case 1: riga.setQuantita(Integer.parseInt(value.toString()));
            case 2: riga.setCosto(Float.parseFloat(value.toString()));
            case 3: riga.setEliminaButton(new JButton());

        }
    }



    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Articolo";
            case 1: return "Quantita";
            case 2: return "Costo totale";
            case 3: return "Elimina";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 3 ) return JButton.class;

        return Object.class;
    }
}
