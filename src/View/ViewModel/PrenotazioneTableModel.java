package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneTableModel extends AbstractTableModel {

    private List<RigaPrenotazione> righe = new ArrayList<RigaPrenotazione>();

    public List<RigaPrenotazione> getRighe() {
        return righe;
    }

    public PrenotazioneTableModel(List<RigaPrenotazione> righe) {
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

        RigaPrenotazione riga = righe.get(rowIndex);
        SimpleDateFormat format = new SimpleDateFormat("'Prenotato il' dd MMM yyyy 'alle' HH:mm:ss");


        switch(columnIndex) {

            case 0: return riga.getUsernameCliente();
            case 1: return riga.getNomeProdotto();
            case 2: return riga.getQuantitaProdotto();
            case 3: return format.format(riga.getData());
            case 4: return riga.getModificaButton();
            case 5: return riga.getEliminaButton();
        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaPrenotazione riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 1: riga.setUsernameCliente(value.toString());
            case 2: riga.setNomeProdotto(value.toString());
            case 3: riga.setQuantitaProdotto(Integer.parseInt(value.toString()));
            case 4: riga.setModificaButton(new JButton());
            case 5: riga.setEliminaButton(new JButton());

        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Cliente";
            case 1: return "Prodotto";
            case 2: return "Quantità Prodotto";
            case 3: return "Data Prenotazione";
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
