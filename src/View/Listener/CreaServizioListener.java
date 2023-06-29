package View.Listener;

import Business.AbstractFactory.FactoryProvider;
import Business.ArticoloBusiness;
import Business.CategoriaBusiness;
import Business.FornitoreBusiness;
import Business.ImmagineBusiness;
import Business.Results.ArticoloResult;
import Business.Results.CategoriaResult;
import Business.Results.FornitoreResult;
import Business.Results.ImmagineResult;
import Model.Servizio;
import View.MainFrame;
import View.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CreaServizioListener implements ActionListener {
    private MainFrame frame;
    private JTextField nomeServizioField;
    private JTextField descrizioneField;
    private JTextField prezzoField;
    private JComboBox<String> fornitoreBox;
    private JComboBox<String> categoriaServizioBox;
    private ArrayList<File> files;
    private Servizio servizio;

    public CreaServizioListener(MainFrame frame, JTextField nomeServizioField, JTextField descrizioneField, JTextField prezzoField, JComboBox<String> fornitoreBox, JComboBox<String> categoriaServizioBox, ArrayList<File> files) {
        this.frame = frame;
        this.nomeServizioField = nomeServizioField;
        this.descrizioneField = descrizioneField;
        this.prezzoField = prezzoField;
        this.fornitoreBox = fornitoreBox;
        this.categoriaServizioBox = categoriaServizioBox;
        this.files = files;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Float.parseFloat(prezzoField.getText());
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
            return;
        }

        ArticoloResult articoloResult;
        servizio = (Servizio) FactoryProvider.getFactory(FactoryProvider.FactoryType.SERVIZIO).crea();
        servizio.setName(nomeServizioField.getText());
        servizio.setDescrizione(descrizioneField.getText());
        servizio.setPrezzo(Float.valueOf(prezzoField.getText()));

        if(fornitoreBox.getSelectedItem() != null && !fornitoreBox.getSelectedItem().toString().isBlank()){
            FornitoreResult fornitoreResult = FornitoreBusiness.getInstance().caricaFornitoreByNome(fornitoreBox.getSelectedItem().toString());
            if (fornitoreResult.getResult().equals(FornitoreResult.Result.FORNITORI_CARICATI))
                servizio.setFornitore(fornitoreResult.getFornitori().get(0));
            else JOptionPane.showMessageDialog(this.frame, fornitoreResult.getMessage());
        }

        if(!categoriaServizioBox.getSelectedItem().toString().equals("Nessuna Categoria") ) {
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategoriaServizioByName(categoriaServizioBox.getSelectedItem().toString());
            if (categoriaResult.getResult().equals(CategoriaResult.Result.CATEGORIE_CARICATE))
                servizio.setCategoria(categoriaResult.getCategorieServizio().get(0));
            else JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());
        }

        articoloResult = ArticoloBusiness.getInstance().addArticolo(servizio);
        if(articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)){
            ImmagineResult immagineResult;
            for (int i = 0; i < files.size(); i++) {
                immagineResult = ImmagineBusiness.getInstance().addImmagine(files.get(i), servizio.getIdArticolo());
                if(!immagineResult.getResult().equals(ImmagineResult.Result.ADD_OK))
                    break;
            }
            this.frame.mostraPannelloAttuale(new MenuPanel(this.frame));
        }
        JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
    }
}
