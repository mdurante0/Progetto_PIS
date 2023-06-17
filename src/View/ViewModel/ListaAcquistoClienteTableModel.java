package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ListaAcquistoClienteTableModel extends AbstractTableModel {

    private List<RigaListaAcquistoCliente> righe = new ArrayList<RigaListaAcquistoCliente>();

    public List<RigaListaAcquistoCliente> getRighe() {
        return righe;
    }

    public ListaAcquistoClienteTableModel(List<RigaListaAcquistoCliente> righe) {
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

        RigaListaAcquistoCliente riga = righe.get(rowIndex);


        switch(columnIndex) {

            case 0: return riga.getNomeLista();
            case 1: return riga.getPagata();
            case 2: return riga.getModificaButton();
            case 3: return riga.getEliminaButton();

        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaListaAcquistoCliente riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 1: riga.setNomeLista(value.toString());
            case 2: riga.setPagata(value.toString());
            case 3: riga.setModificaButton(new JButton());
            case 4: riga.setEliminaButton(new JButton());

        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Nome Lista";
            case 1: return "Stato Pagamento";
            case 2: return "Visualizza";
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
