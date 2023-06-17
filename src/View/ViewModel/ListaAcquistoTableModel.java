package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListaAcquistoTableModel extends AbstractTableModel {

    private List<RigaListaAcquisto> righe = new ArrayList<RigaListaAcquisto>();

    public List<RigaListaAcquisto> getRighe() {
        return righe;
    }

    public ListaAcquistoTableModel(List<RigaListaAcquisto> righe) {
        this.righe = righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaListaAcquisto riga = righe.get(rowIndex);


        switch(columnIndex) {

            case 0: return riga.getUsernameCliente();
            case 1: return riga.getNomeLista();
            case 2: return riga.getPagata();
            case 3: return riga.getVisualizzaButton();
            case 4: return riga.getEliminaButton();

        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaListaAcquisto riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0: riga.setUsernameCliente(value.toString());
            case 1: riga.setNomeLista(value.toString());
            case 2: riga.setPagata(new JButton());
            case 3: riga.setVisualizzaButton(new JButton());
            case 4: riga.setEliminaButton(new JButton());

        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Cliente";
            case 1: return "Nome Lista";
            case 2: return "Stato Pagamento";
            case 3: return "Visualizza";
            case 4: return "Elimina";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 2) return JButton.class;
        if(columnIndex == 3) return JButton.class;
        if(columnIndex == 3) return JButton.class;

        return Object.class;
    }
}
