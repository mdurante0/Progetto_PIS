package View;

import Business.ImmagineBusiness;
import Business.Results.ImmagineResult;
import View.ViewModel.RigaMagazzino;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MagazzinoTableModel extends AbstractTableModel {

    private List<RigaMagazzino> righe = new ArrayList<RigaMagazzino>();

    public List<RigaMagazzino> getRighe() {
        return righe;
    }

    public MagazzinoTableModel(List<RigaMagazzino> righe) {
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

        RigaMagazzino riga = righe.get(rowIndex);

        switch(columnIndex) {

            case 0: return riga.getQuantitaCorsie();
            case 1: return riga.getQuantitaScaffali();
            case 2: return riga.getIndirizzo();
            case 3: return riga.getModificaButton();
            case 4: return riga.getEliminaButton();
        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaMagazzino riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0:  riga.setQuantitaCorsie(Integer.parseInt(value.toString()));
            case 1:  riga.setQuantitaScaffali(Integer.parseInt(value.toString()));
            case 2:  riga.setIndirizzo(value.toString());
            case 3:  riga.setModificaButton(new JButton());
            case 4:  riga.setEliminaButton(new JButton());

        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Quantità Corsie";
            case 1: return "Quantità Scaffali";
            case 2: return "Indirizzo";
            case 3: return "Modifica";
            case 4: return "Elimina";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 4) return JButton.class;
        if(columnIndex == 3) return JButton.class;
        return Object.class;
    }
}
