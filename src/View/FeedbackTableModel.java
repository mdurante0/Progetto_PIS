package View;

import Model.Feedback;
import View.ViewModel.RigaFeedback;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackTableModel extends AbstractTableModel {

    private List<RigaFeedback> righe = new ArrayList<>();

    public List<RigaFeedback> getRighe() {
        return righe;
    }

    public FeedbackTableModel(List<RigaFeedback> righe) {
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

        RigaFeedback riga = righe.get(rowIndex);
        SimpleDateFormat format = new SimpleDateFormat("'Commentato il' dd MMM yyyy 'alle' HH:mm:ss");

        switch(columnIndex) {
            case 0: return format.format(riga.getData());
            case 1: return riga.getUsernameCliente();
            case 2: return riga.getCommento();
            case 3: return String.valueOf(riga.getPunteggio());
            case 4: return riga.getRisposta();
        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaFeedback riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0: riga.setData(new Date());
            case 1: riga.setUsernameCliente(value.toString());
            case 2: riga.setCommento(value.toString());
            case 3: riga.setPunteggio(Feedback.Punteggio.valueOf(value.toString()));
            case 4: riga.setRisposta(value.toString());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Data";
            case 1: return "Cliente";
            case 2: return "Commento";
            case 3: return "Voto";
            case 4: return "Risposta del manager";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 3) return Feedback.Punteggio.class;
        return Object.class;
    }
}
