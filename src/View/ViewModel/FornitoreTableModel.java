package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FornitoreTableModel extends AbstractTableModel {

    private List<RigaFornitore> righe = new ArrayList<RigaFornitore>();

    public List<RigaFornitore> getRighe() {
        return righe;
    }

    public FornitoreTableModel(List<RigaFornitore> righe) {
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

        RigaFornitore riga = righe.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return riga.getNomeFornitore();
            case 1:
                return riga.getEmailFornitore();
            case 2:
                return riga.getSitoFornitore();
            case 3:
                return riga.getDescrizione();
            case 4:
                return riga.getModificaButton();
            case 5:
                return riga.getEliminaButton();

        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaFornitore riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0: riga.setNomeFornitore(value.toString());
            case 1: riga.setEmailFornitore(value.toString());
            case 2: riga.setSitoFornitore(value.toString());
            case 3: riga.setDescrizione(value.toString());
            case 4: riga.setModificaButton(new JButton());
            case 5: riga.setEliminaButton(new JButton());

        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Nome";
            case 1: return "Email";
            case 2: return "Sito";
            case 3: return "Descrizione";
            case 4: return "Modifica";
            case 5: return "Elimina";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 5) return JButton.class;
        if(columnIndex == 4) return JButton.class;
        return Object.class;
    }
}
