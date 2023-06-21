package View.Listener;

import Business.AbstractFactory.FactoryProvider;
import Business.*;
import Business.Results.*;
import Model.Collocazione;
import Model.composite.Prodotto;
import View.MainFrame;
import View.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CreaNuovoProdottoListener implements ActionListener {
    private MainFrame frame;
    private JTextField nomeProdottoField;
    private JTextField descrizioneField;
    private JTextField prezzoField;
    private JTextField quantitaField;
    private JComboBox<String> produttoreBox;
    private JComboBox<String> categoriaProdottoBox;
    private JComboBox<String> puntoVenditaBox;
    private JTextField corsiaField;
    private JTextField scaffaleField;
    private ArrayList<File> files;
    private Prodotto prodotto;

    public CreaNuovoProdottoListener(MainFrame frame, JTextField nomeProdottoField, JTextField descrizioneField, JTextField prezzoField, JTextField quantitaField, JComboBox<String> produttoreBox, JComboBox<String> categoriaProdottoBox, JComboBox<String> puntoVenditaBox, JTextField corsiaField, JTextField scaffaleField, ArrayList<File> files) {
        this.frame = frame;
        this.nomeProdottoField = nomeProdottoField;
        this.descrizioneField = descrizioneField;
        this.prezzoField = prezzoField;
        this.quantitaField = quantitaField;
        this.produttoreBox = produttoreBox;
        this.categoriaProdottoBox = categoriaProdottoBox;
        this.puntoVenditaBox = puntoVenditaBox;
        this.corsiaField = corsiaField;
        this.scaffaleField = scaffaleField;
        this.files = files;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Integer.parseInt(quantitaField.getText());
            Float.parseFloat(prezzoField.getText());
            Integer.parseInt(corsiaField.getText());
            Integer.parseInt(scaffaleField.getText());
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
            return;
        }

        ArticoloResult articoloResult;
        prodotto = (Prodotto) FactoryProvider.getFactory(FactoryProvider.FactoryType.PRODOTTO).crea();
        prodotto.setName(nomeProdottoField.getText());
        prodotto.setDescrizione(descrizioneField.getText());
        prodotto.setPrezzo(Float.valueOf(prezzoField.getText()));
        prodotto.setQuantita(Integer.parseInt(quantitaField.getText()));

        //Caricamento Produttore
        if(produttoreBox.getSelectedItem() != null && !produttoreBox.getSelectedItem().toString().isBlank()){
            ProduttoreResult produttoreResult = ProduttoreBusiness.getInstance().caricaProduttoreByNome(produttoreBox.getSelectedItem().toString());
            if (produttoreResult.getResult().equals(ProduttoreResult.Result.PRODUTTORI_CARICATI))
                prodotto.setProduttore(produttoreResult.getProduttori().get(0));
            else JOptionPane.showMessageDialog(this.frame, produttoreResult.getMessage());
        }

        //Caricamento Categoria
        if(categoriaProdottoBox.getSelectedItem() != null && !categoriaProdottoBox.getSelectedItem().toString().isBlank() ) {
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategoriaProdottoByName(categoriaProdottoBox.getSelectedItem().toString());
            if (categoriaResult.getResult().equals(CategoriaResult.Result.CATEGORIE_CARICATE))
                prodotto.setCategoria(categoriaResult.getCategorieProdotto().get(0));
            else JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());
        }

        //Caricamento Punto Vendita
        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByNome(puntoVenditaBox.getSelectedItem().toString());
        if (puntoVenditaResult.getResult().equals(PuntoVenditaResult.Result.SALEPOINT_CARICATI)) {

            //Caricamento Magazzino
            MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByPuntoVendita(puntoVenditaResult.getPuntiVendita().get(0));
            prodotto.setMagazzino(magazzinoResult.getMagazzini().get(0));
            if (magazzinoResult.getResult().equals(MagazzinoResult.Result.MAGAZZINI_CARICATI)) {

                //Inserimento Collocazione
                Collocazione collocazione = new Collocazione(Integer.parseInt(corsiaField.getText()), Integer.parseInt(scaffaleField.getText()), magazzinoResult.getMagazzini().get(0));
                CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().addCollocazione(collocazione);
                if (collocazioneResult.getResult().equals(CollocazioneResult.Result.ADD_OK)) {
                    prodotto.setCollocazione(collocazione);

                    //Inserimento Prodotto
                    articoloResult = ArticoloBusiness.getInstance().addArticolo(prodotto);
                    if(articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK) || articoloResult.getResult().equals(ArticoloResult.Result.ITEM_ALREADY_EXISTS)){

                        //Inserimento Prodotto nel Magazzino
                        articoloResult = ArticoloBusiness.getInstance().addProdottoToMagazzino(prodotto,magazzinoResult.getMagazzini().get(0).getIdMagazzino());
                        if(articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)) {

                            //Inserimento Immagini
                            ImmagineResult immagineResult;
                            for (int i = 0; i < files.size(); i++) {
                                immagineResult = ImmagineBusiness.getInstance().addImmagine(files.get(i), prodotto.getIdArticolo());
                                if(!immagineResult.getResult().equals(ImmagineResult.Result.ADD_OK))
                                    break;
                                else prodotto.getImmagini().get(i).setFile(files.get(i));
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
