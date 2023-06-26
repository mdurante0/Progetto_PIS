package View.ViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PuntoVenditaTableModel extends AbstractTableModel {

    private List<RigaPuntoVendita> righe = new ArrayList<RigaPuntoVendita>();

    public List<RigaPuntoVendita> getRighe() {
        return righe;
    }

    public PuntoVenditaTableModel(List<RigaPuntoVendita> righe) {
        this.righe = righe;
    }

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaPuntoVendita riga = righe.get(rowIndex);

        switch(columnIndex) {

            case 0: return riga.getNomePuntoVendita();
            case 1: return riga.getManager();
            case 2: return riga.getCitta();
            case 3: return riga.getIndirizzo();
            case 4: return riga.getMagazzino();
            case 5: return riga.getTelefono();
            case 6: return riga.getModificaButton();
            case 7: return riga.getEliminaButton();
        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaPuntoVendita riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0: riga.setNomePuntoVendita(value.toString());
            case 1: riga.setManager(value.toString());
            case 2: riga.setCitta(value.toString());
            case 3: riga.setIndirizzo(value.toString());
            case 4: riga.setMagazzino(value.toString());
            case 5: riga.setTelefono(value.toString());
            case 6: riga.setModificaButton(new JButton());
            case 7: riga.setEliminaButton(new JButton());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Nome punto vendita";
            case 1: return "Manager";
            case 2: return "Città";
            case 3: return "Indirizzo punto vendita";
            case 4: return "Indirizzo magazzino";
            case 5: return "Telefono";
            case 6: return "Modifica";
            case 7: return "Elimina";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 6) return JButton.class;
        if(columnIndex == 7) return JButton.class;
        return Object.class;
    }
}
