package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ManagerTableModel extends AbstractTableModel {

    private List<RigaManager> righe = new ArrayList<RigaManager>();

    public List<RigaManager> getRighe() {
        return righe;
    }

    public ManagerTableModel(List<RigaManager> righe) {
        this.righe = righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaManager riga = righe.get(rowIndex);


        switch(columnIndex) {

            case 0: return riga.getUsername();
            case 1: return riga.getEmail();
            case 2: return riga.getNomeManager();
            case 3: return riga.getCongnomeManager();
            case 4: return riga.getDurataIncarico();
            case 5: return riga.getModificaButton();
            case 6: return riga.getEliminaButton();

        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaManager riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0: riga.setUsername(value.toString());
            case 1: riga.setEmail(value.toString());
            case 2: riga.setNomeManager(value.toString());
            case 3: riga.setCongnomeManager(value.toString());
            case 4: riga.setDurataIncarico(Integer.parseInt(value.toString()));
            case 5: riga.setModificaButton(new JButton());
            case 6: riga.setEliminaButton(new JButton());

        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Username";
            case 1: return "Email";
            case 2: return "Nome";
            case 3: return "Cognome";
            case 4: return "Durata incarico (anni)";
            case 5: return "Modifica";
            case 6: return "Elimina";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 5) return JButton.class;
        if(columnIndex == 6) return JButton.class;

        return Object.class;
    }
}
