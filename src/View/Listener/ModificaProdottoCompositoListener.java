package View.Listener;

import Business.*;
import Business.Results.*;
import Model.PuntoVendita;
import Model.composite.ProdottoComposito;
import View.DettagliPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ModificaProdottoCompositoListener implements ActionListener {
    private MainFrame frame;
    private JTextField nomeProdottoCompositoField;
    private JTextField descrizioneField;
    private JTextField quantitaField;
    private JComboBox<String> categoriaProdottoBox;
    private JComboBox<String> puntoVenditaBox;
    private JTextField corsiaField;
    private JTextField scaffaleField;
    private ArrayList<File> files;
    private ArrayList<JComboBox<String>> componentiBoxes;
    private ArrayList<JTextField> quantitaComponentiFields;
    private ProdottoComposito prodottoComposito;

    public ModificaProdottoCompositoListener(MainFrame frame, JTextField nomeProdottoCompositoField, JTextField descrizioneField, JTextField quantitaField, JComboBox<String> categoriaProdottoBox, JComboBox<String> puntoVenditaBox, JTextField corsiaField, JTextField scaffaleField, ArrayList<File> files, ArrayList<JComboBox<String>> componentiBoxes, ArrayList<JTextField> quantitaComponentiFields, ProdottoComposito prodottoComposito) {
        this.frame = frame;
        this.nomeProdottoCompositoField = nomeProdottoCompositoField;
        this.descrizioneField = descrizioneField;
        this.quantitaField = quantitaField;
        this.categoriaProdottoBox = categoriaProdottoBox;
        this.puntoVenditaBox = puntoVenditaBox;
        this.corsiaField = corsiaField;
        this.scaffaleField = scaffaleField;
        this.files = files;
        this.componentiBoxes = componentiBoxes;
        this.quantitaComponentiFields = quantitaComponentiFields;
        this.prodottoComposito = prodottoComposito;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ArticoloResult articoloResult;
        prodottoComposito.setName(nomeProdottoCompositoField.getText());
        prodottoComposito.setDescrizione(descrizioneField.getText());

        //Caricamento Categoria
        if (categoriaProdottoBox.getSelectedItem() != null && !categoriaProdottoBox.getSelectedItem().toString().isBlank()) {
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategoriaProdottoByName(categoriaProdottoBox.getSelectedItem().toString());

            if (categoriaResult.getResult().equals(CategoriaResult.Result.CATEGORIE_CARICATE))
                prodottoComposito.setCategoria(categoriaResult.getCategorieProdotto().get(0));
            else{
                JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());
                return;
            }

        } else if (prodottoComposito.getCategoria().getNome() != null) {
            articoloResult = ArticoloBusiness.getInstance().removeArticoloFromCategoria(prodottoComposito);

            if (!articoloResult.getResult().equals(ArticoloResult.Result.DELETE_OK)) {
                JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                return;
            }
        }

        //sottoprodotti
        ArrayList<String> nomiSottoprodotti = new ArrayList<>();
        for (JComboBox<String> componentBox :
                componentiBoxes) {
            nomiSottoprodotti.add(componentBox.getSelectedItem().toString());
        }
        CatalogoResult catalogoResult = CatalogoBusiness.getInstance().caricaCatalogoProdottiByNomi(nomiSottoprodotti);
        prodottoComposito.setSottoprodotti(catalogoResult.getListaProdotti());
        for (int i = 0; i < prodottoComposito.getSottoprodotti().size(); i++) {
            try {
                prodottoComposito.getSottoprodotti().get(i).setQuantita(Integer.parseInt(quantitaComponentiFields.get(i).getText()));
            } catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
                return;
            }
        }

        //Aggiornamento Prodotto
        articoloResult = ArticoloBusiness.getInstance().updateArticolo(prodottoComposito);
        if (!articoloResult.getResult().equals(ArticoloResult.Result.UPDATE_OK)) {
            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
            return;
        }

        //Rimozione vecchie Immagini
        ImmagineResult immagineResult;
        if (prodottoComposito.getImmagini().size() != 0) {
            immagineResult = ImmagineBusiness.getInstance().removeImmagineByArticolo(prodottoComposito);
            if (!immagineResult.getResult().equals(ImmagineResult.Result.REMOVE_OK)) {
                JOptionPane.showMessageDialog(this.frame, immagineResult.getMessage());
                return;
            }
        }
        //Inserimento delle nuove immagini
        for (File file : files) {
            immagineResult = ImmagineBusiness.getInstance().addImmagine(file, prodottoComposito.getIdArticolo());
            if (!immagineResult.getResult().equals(ImmagineResult.Result.ADD_OK)) {
                JOptionPane.showMessageDialog(this.frame, immagineResult.getMessage());
                return;
            }
        }

        //Caricamento Punto Vendita
        PuntoVenditaResult puntoVenditaResult;
        if (puntoVenditaBox.getSelectedItem() != null && !puntoVenditaBox.getSelectedItem().toString().isBlank()) {

            puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByNome(puntoVenditaBox.getSelectedItem().toString());
            if (puntoVenditaResult.getResult().equals(PuntoVenditaResult.Result.SALEPOINT_CARICATI)) {
                PuntoVendita puntoVendita = puntoVenditaResult.getPuntiVendita().get(0);
                //Rimozione dal vecchio magazzino
                if(prodottoComposito.getMagazzino().getIndirizzo() != null) {
                    articoloResult = ArticoloBusiness.getInstance().removeProdottoFromMagazzino(prodottoComposito, prodottoComposito.getMagazzino().getIdMagazzino());
                    if (!articoloResult.getResult().equals(ArticoloResult.Result.DELETE_OK)) {
                        JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                        return;
                    }
                }
                //Caricamento nuovo Magazzino
                MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByPuntoVendita(puntoVendita);
                prodottoComposito.setMagazzino(magazzinoResult.getMagazzini().get(0));
                if (magazzinoResult.getResult().equals(MagazzinoResult.Result.MAGAZZINI_CARICATI)) {

                    try {
                        Integer.parseInt(quantitaField.getText());
                        Integer.parseInt(corsiaField.getText());
                        Integer.parseInt(scaffaleField.getText());
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
                        return;
                    }
                    prodottoComposito.setQuantita(Integer.parseInt(quantitaField.getText()));

                    //Aggiornamento collocazione
                    CollocazioneResult collocazioneResult;
                    prodottoComposito.getCollocazione().setIdProdotto(prodottoComposito.getIdArticolo());
                    prodottoComposito.getCollocazione().setCorsia(Integer.parseInt(corsiaField.getText()));
                    prodottoComposito.getCollocazione().setScaffale(Integer.parseInt(scaffaleField.getText()));

                    if(prodottoComposito.getCollocazione().getMagazzino() == null){
                        prodottoComposito.getCollocazione().setMagazzino(magazzinoResult.getMagazzini().get(0));
                        collocazioneResult = CollocazioneBusiness.getInstance().addCollocazione(prodottoComposito.getCollocazione());
                    }else {
                        prodottoComposito.getCollocazione().setMagazzino(magazzinoResult.getMagazzini().get(0));
                        collocazioneResult = CollocazioneBusiness.getInstance().updateCollocazione(prodottoComposito.getCollocazione());
                    }
                    if (collocazioneResult.getResult().equals(CollocazioneResult.Result.UPDATE_OK) || collocazioneResult.getResult().equals(CollocazioneResult.Result.ADD_OK)) {

                        //Inserimento Prodotto nel nuovo Magazzino (collocazione e quantità)
                        articoloResult = ArticoloBusiness.getInstance().addProdottoToMagazzino(prodottoComposito, magazzinoResult.getMagazzini().get(0).getIdMagazzino());
                        if (!articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)) {
                            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                            collocazioneResult = CollocazioneBusiness.getInstance().removeCollocazione(prodottoComposito.getCollocazione());
                            if(!collocazioneResult.getResult().equals(CollocazioneResult.Result.DELETE_OK)){
                                JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(this.frame, "Prodotto inserito con successo in " + puntoVendita.getNome());
                            this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, prodottoComposito, puntoVendita));
                        }
                    } else JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                } else JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());
            } else JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());

        } else if (prodottoComposito.getMagazzino().getIndirizzo() != null){

            //Rimozione dal magazzino
            articoloResult = ArticoloBusiness.getInstance().removeProdottoFromMagazzino(prodottoComposito, prodottoComposito.getMagazzino().getIdMagazzino());
            if (!articoloResult.getResult().equals(ArticoloResult.Result.DELETE_OK)) {
                JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                return;
            }
            CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().removeCollocazione(prodottoComposito.getCollocazione());
            if(!collocazioneResult.getResult().equals(CollocazioneResult.Result.DELETE_OK)){
                JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                return;
            }
            this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, prodottoComposito, null));

        }else this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, prodottoComposito, null));

        JOptionPane.showMessageDialog(this.frame, "Prodotto modificato con successo");
    }
}
