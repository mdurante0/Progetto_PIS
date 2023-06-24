package View.Listener;

import Business.AbstractFactory.FactoryProvider;
import Business.*;
import Business.Results.*;
import Model.Collocazione;
import Model.composite.ProdottoComposito;
import View.MainFrame;
import View.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CreaNuovoProdottoCompositoListener implements ActionListener {

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

    public CreaNuovoProdottoCompositoListener(MainFrame frame, JTextField nomeProdottoCompositoField, JTextField descrizioneField, JTextField quantitaField, JComboBox<String> categoriaProdottoBox, JComboBox<String> puntoVenditaBox, JTextField corsiaField, JTextField scaffaleField, ArrayList<File> files, ArrayList<JComboBox<String>> componentiBoxes, ArrayList<JTextField> quantitaComponentiFields) {
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ArrayList<Integer> quantitaComponenti = new ArrayList<>();
        try{
            Integer.parseInt(quantitaField.getText());
            Integer.parseInt(corsiaField.getText());
            Integer.parseInt(scaffaleField.getText());

            //Caricamento quantità componenti
            for (JTextField quantitaField : quantitaComponentiFields){
                quantitaComponenti.add(Integer.parseInt(quantitaField.getText()));
            }
            
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
            return;
        }

        if(Integer.parseInt(quantitaField.getText()) <= 0 || Integer.parseInt(corsiaField.getText()) <= 0 || Integer.parseInt(scaffaleField.getText()) <= 0){
            JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
            return;
        }

        ArticoloResult articoloResult;
        prodottoComposito = (ProdottoComposito) FactoryProvider.getFactory(FactoryProvider.FactoryType.PRODOTTO_COMPOSITO).crea();
        prodottoComposito.setName(nomeProdottoCompositoField.getText());
        prodottoComposito.setDescrizione(descrizioneField.getText());
        prodottoComposito.setQuantita(Integer.parseInt(quantitaField.getText()));

        //Caricamento componenti
        ArrayList<String> nomiSottoprodotti = new ArrayList<>();
        for (JComboBox<String> componentiBox : componentiBoxes) {
            nomiSottoprodotti.add(componentiBox.getSelectedItem().toString());
        }
        CatalogoResult result = CatalogoBusiness.getInstance().caricaCatalogoProdottiByNomi(nomiSottoprodotti);
        for (int i = 0; i < result.getListaProdotti().size(); i++) {
            result.getListaProdotti().get(i).setQuantita(quantitaComponenti.get(i));
        }
        prodottoComposito.setSottoprodotti(result.getListaProdotti());
        
        //Caricamento Categoria
        if(categoriaProdottoBox.getSelectedItem() != null && !categoriaProdottoBox.getSelectedItem().toString().isBlank() ) {
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategoriaProdottoByName(categoriaProdottoBox.getSelectedItem().toString());
            if (categoriaResult.getResult().equals(CategoriaResult.Result.CATEGORIE_CARICATE))
                prodottoComposito.setCategoria(categoriaResult.getCategorieProdotto().get(0));
            else JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());
        }

        //Caricamento Punto Vendita
        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByNome(puntoVenditaBox.getSelectedItem().toString());
        if (puntoVenditaResult.getResult().equals(PuntoVenditaResult.Result.SALEPOINT_CARICATI)) {

            //Caricamento Magazzino
            MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByPuntoVendita(puntoVenditaResult.getPuntiVendita().get(0));
            prodottoComposito.setMagazzino(magazzinoResult.getMagazzini().get(0));
            if (magazzinoResult.getResult().equals(MagazzinoResult.Result.MAGAZZINI_CARICATI)) {

                //Inserimento Collocazione
                Collocazione collocazione = new Collocazione(Integer.parseInt(corsiaField.getText()), Integer.parseInt(scaffaleField.getText()), magazzinoResult.getMagazzini().get(0));
                CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().addCollocazione(collocazione);
                if (collocazioneResult.getResult().equals(CollocazioneResult.Result.ADD_OK)) {
                    prodottoComposito.setCollocazione(collocazione);

                    //Inserimento Prodotto Composito
                    articoloResult = ArticoloBusiness.getInstance().addArticolo(prodottoComposito);
                    if(articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK) || articoloResult.getResult().equals(ArticoloResult.Result.ITEM_ALREADY_EXISTS)){

                        //Inserimento Prodotto Composito nel Magazzino
                        articoloResult = ArticoloBusiness.getInstance().addProdottoToMagazzino(prodottoComposito,magazzinoResult.getMagazzini().get(0).getIdMagazzino());
                        if(articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)) {

                            //Inserimento Immagini
                            ImmagineResult immagineResult;
                            for (int i = 0; i < files.size(); i++) {
                                immagineResult = ImmagineBusiness.getInstance().addImmagine(files.get(i), prodottoComposito.getIdArticolo());
                                if(!immagineResult.getResult().equals(ImmagineResult.Result.ADD_OK))
                                    break;
                            }

                            this.frame.mostraPannelloAttuale(new MenuPanel(this.frame));
                        } else collocazioneResult = CollocazioneBusiness.getInstance().removeCollocazione(collocazione);
                    } else collocazioneResult = CollocazioneBusiness.getInstance().removeCollocazione(collocazione);
                    JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                } else JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
            } else JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());
        } else JOptionPane.showMessageDialog(this.frame, puntoVenditaResult.getMessage());
    }
}
