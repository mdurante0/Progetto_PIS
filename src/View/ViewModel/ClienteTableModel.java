package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel {

    private List<RigaCliente> righe = new ArrayList<RigaCliente>();

    public List<RigaCliente> getRighe() {
        return righe;
    }

    public ClienteTableModel(List<RigaCliente> righe) {
        this.righe = righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaCliente riga = righe.get(rowIndex);


        switch(columnIndex) {

            case 0: return riga.getUsername();
            case 1: return riga.getEmail();
            case 2: return riga.getNomeCliente();
            case 3: return riga.getCongnomeCliente();
            case 4: return riga.getAbilitazione();
            case 5: return riga.getEliminaButton();

        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaCliente riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0: riga.setUsername(value.toString());
            case 1: riga.setEmail(value.toString());
            case 2: riga.setNomeCliente(value.toString());
            case 3: riga.setCongnomeCliente(value.toString());
            case 4: riga.setAbilitazione(new JButton());
            case 5: riga.setEliminaButton(new JButton());

        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Username";
            case 1: return "Email";
            case 2: return "Nome";
            case 3: return "Cognome";
            case 4: return "Abilitazione";
            case 5: return "Elimina";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 4) return JButton.class;
        if(columnIndex == 5) return JButton.class;

        return Object.class;
    }
}
