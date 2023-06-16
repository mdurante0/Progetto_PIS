package View.ViewModel;

import Business.ImmagineBusiness;
import Business.Results.ImmagineResult;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CatalogoTableModel extends AbstractTableModel {

    private List<RigaCatalogo> righe = new ArrayList<RigaCatalogo>();

    public List<RigaCatalogo> getRighe() {
        return righe;
    }

    public CatalogoTableModel(List<RigaCatalogo> righe) {
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

        RigaCatalogo riga = righe.get(rowIndex);

        switch(columnIndex) {

            case 0:
                ImmagineResult result = ImmagineBusiness.getInstance().caricaImmaginiByArticolo(riga.getNomeProdotto());
                if(result.getResult() == ImmagineResult.Result.IMMAGINI_CARICATE)
                    return new ImageIcon(result.getListaImmagini().get(0).getPic().getImage().getScaledInstance(70,70, Image.SCALE_SMOOTH));
                else {
                    try {
                        InputStream stream = getClass().getResourceAsStream("/LOGO.PNG");
                        assert stream != null;
                        return new ImageIcon(ImageIO.read(stream).getScaledInstance(70,70,0));
                    } catch (IOException e) {
                        e.printStackTrace();
                        return new ImageIcon();
                    }
                }
            case 1: return riga.getNomeProdotto();
            case 2: return riga.getNomeRifornitore();
            case 3: return riga.getNomeCategoria();
            case 4: return riga.getPrezzo();
            case 5: return riga.getDettagliButton();
        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaCatalogo riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 1: riga.setNomeProdotto(value.toString());
            case 2: riga.setNomeRifornitore(value.toString());
            case 3: riga.setNomeCategoria(value.toString());
            case 4: riga.setPrezzo(Float.parseFloat(value.toString()));
            case 5: riga.setDettagliButton(new JButton());

        }
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "Immagine";
            case 1: return "Nome";
            case 2: return "Produttore o Fornitore";
            case 3: return "Categoria";
            case 4: return "Prezzo (€)";
            case 5: return "Dettagli";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 5) return JButton.class;
        if(columnIndex == 0) return ImageIcon.class;
        return Object.class;
    }
}
