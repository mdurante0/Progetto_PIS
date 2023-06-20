package View.ViewModel;

import Model.Cliente;
import Model.Utente;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ListaAcquistoTableModel extends AbstractTableModel {

    private List<RigaListaAcquisto> righe = new ArrayList<RigaListaAcquisto>();
    private Utente utente;

    public List<RigaListaAcquisto> getRighe() {
        return righe;
    }

    public ListaAcquistoTableModel(List<RigaListaAcquisto> righe, Utente utente) {
        this.righe = righe;
        this.utente = utente;
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

        RigaListaAcquisto riga = righe.get(rowIndex);


        switch (columnIndex) {

            case 0:
                if (utente instanceof Cliente)
                    return riga.getNomeLista();
                else return riga.getUsernameCliente();
            case 1:
                if (utente instanceof Cliente)
                    return riga.getPagata();
                else return riga.getNomeLista();
            case 2:
                if (utente instanceof Cliente)
                    return riga.getDettagliButton();
                else return riga.getPagata();
            case 3:
                return riga.getEliminaButton();

        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaListaAcquisto riga = righe.get(rowIndex);

        switch (columnIndex) {
            case 0:
                if (utente instanceof Cliente)
                    riga.setNomeLista(value.toString());
                else riga.setUsernameCliente(value.toString());
            case 1:
                if (utente instanceof Cliente)
                    riga.setPagata(value.toString());
                else riga.setNomeLista(value.toString());
            case 2:
                if (utente instanceof Cliente)
                    riga.setDettagliButton(new JButton());
                else riga.setPagata(new JButton());
            case 3:
                riga.setEliminaButton(new JButton());

        }
    }



    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0:
                if (utente instanceof Cliente)
                    return "Nome lista";
                else return "Cliente";
            case 1:
                if (utente instanceof Cliente)
                    return "Stato pagamento";
                else return "Nome lista";
            case 2:
                if (utente instanceof Cliente)
                    return "Dettagli";
                else return "Stato pagamento";
            case 3: return "Elimina";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 2 ) return JButton.class;
        if(columnIndex == 3) return JButton.class;

        return Object.class;
    }
}
