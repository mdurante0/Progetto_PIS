package View.Listener;

import Business.*;
import Business.Results.*;
import Model.Servizio;
import View.DettagliPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ModificaServizioListener implements ActionListener {
    private MainFrame frame;
    private JTextField nomeServizioField;
    private JTextField descrizioneField;
    private JTextField prezzoField;
    private JComboBox<String> fornitoreBox;
    private JComboBox<String> categoriaServizioBox;
    private ArrayList<File> files;
    private Servizio servizio;

    public ModificaServizioListener(MainFrame frame, JTextField nomeServizioField, JTextField descrizioneField, JTextField prezzoField, JComboBox<String> fornitoreBox, JComboBox<String> categoriaServizioBox, ArrayList<File> files, Servizio servizio) {
        this.frame = frame;
        this.nomeServizioField = nomeServizioField;
        this.descrizioneField = descrizioneField;
        this.prezzoField = prezzoField;
        this.fornitoreBox = fornitoreBox;
        this.categoriaServizioBox = categoriaServizioBox;
        this.files = files;
        this.servizio = servizio;
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
        servizio.setName(nomeServizioField.getText());
        servizio.setDescrizione(descrizioneField.getText());
        servizio.setPrezzo(Float.valueOf(prezzoField.getText()));

        //Caricamento Produttore
        if(fornitoreBox.getSelectedItem() != null && !fornitoreBox.getSelectedItem().toString().isBlank()){
            FornitoreResult fornitoreResult = FornitoreBusiness.getInstance().caricaFornitoreByNome(fornitoreBox.getSelectedItem().toString());
            if (fornitoreResult.getResult().equals(FornitoreResult.Result.FORNITORI_CARICATI))
                servizio.setFornitore(fornitoreResult.getFornitori().get(0));
            else JOptionPane.showMessageDialog(this.frame, fornitoreResult.getMessage());
        }

        //Caricamento Categoria
        if(categoriaServizioBox.getSelectedItem() != null && !categoriaServizioBox.getSelectedItem().toString().isBlank() ) {
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategoriaServizioByName(categoriaServizioBox.getSelectedItem().toString());
            if (categoriaResult.getResult().equals(CategoriaResult.Result.CATEGORIE_CARICATE))
                servizio.setCategoria(categoriaResult.getCategorieServizio().get(0));
            else JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());
        } else if (servizio.getCategoria().getNome() != null){
            articoloResult = ArticoloBusiness.getInstance().removeArticoloFromCategoria(servizio);
            if(!articoloResult.getResult().equals(ArticoloResult.Result.DELETE_OK)){
                JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                return;
            }
        }

        //Aggiornamento Servizio
        articoloResult = ArticoloBusiness.getInstance().updateArticolo(servizio);
        if (articoloResult.getResult().equals(ArticoloResult.Result.UPDATE_OK)) {

            //Rimozione vecchie Immagini
            ImmagineResult immagineResult;
            if(servizio.getImmagini().size() != 0) {
                immagineResult = ImmagineBusiness.getInstance().removeImmagineByArticolo(servizio);
                if (!immagineResult.getResult().equals(ImmagineResult.Result.REMOVE_OK)){
                    JOptionPane.showMessageDialog(this.frame, immagineResult.getMessage());
                    return;
                }
            }

            //Inserimento delle nuove immagini
            for (int i = 0; i < files.size(); i++) {
                immagineResult = ImmagineBusiness.getInstance().addImmagine(files.get(i), servizio.getIdArticolo());
                if (!immagineResult.getResult().equals(ImmagineResult.Result.ADD_OK))
                    break;
            }
        }else JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());

        JOptionPane.showMessageDialog(this.frame, "Servizio modificato con successo");
        this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, servizio,null));
    }
}