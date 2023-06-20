package View.Listener;

import Business.*;
import Business.Results.*;
import Model.Collocazione;
import Model.composite.Prodotto;
import View.DettagliPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ModificaProdottoListener implements ActionListener {
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

    public ModificaProdottoListener(MainFrame frame, JTextField nomeProdottoField, JTextField descrizioneField, JTextField prezzoField, JTextField quantitaField, JComboBox<String> produttoreBox, JComboBox<String> categoriaProdottoBox, JComboBox<String> puntoVenditaBox, JTextField corsiaField, JTextField scaffaleField, ArrayList<File> files, Prodotto prodotto) {
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
        this.prodotto = prodotto;
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

                //Rimozione vecchia Collocazione
                CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().removeCollocazione(prodotto.getCollocazione());
                if(collocazioneResult.getResult().equals(CollocazioneResult.Result.DELETE_OK)) {

                    //Inserimento nuova collocazione
                    Collocazione collocazione = new Collocazione(Integer.parseInt(corsiaField.getText()), Integer.parseInt(scaffaleField.getText()), magazzinoResult.getMagazzini().get(0));
                    collocazioneResult = CollocazioneBusiness.getInstance().addCollocazione(collocazione);
                    if (collocazioneResult.getResult().equals(CollocazioneResult.Result.ADD_OK)) {
                        prodotto.setCollocazione(collocazione);

                        //Aggiornamento Prodotto
                        articoloResult = ArticoloBusiness.getInstance().updateArticolo(prodotto);
                        if (articoloResult.getResult().equals(ArticoloResult.Result.UPDATE_OK)) {

                            //Aggiornamento Prodotto nel Magazzino (collocazione e quantità)
                            articoloResult = ArticoloBusiness.getInstance().updateProdottoInMagazzino(prodotto, magazzinoResult.getMagazzini().get(0));
                            if (articoloResult.getResult().equals(ArticoloResult.Result.UPDATE_OK)) {

                                //Inserimento Immagini
                                ImmagineResult immagineResult = ImmagineBusiness.getInstance().removeImmagineByArticolo(prodotto);
                                if(immagineResult.getResult().equals(ImmagineResult.Result.REMOVE_OK)) {
                                    for (int i = 0; i < files.size(); i++) {
                                        immagineResult = ImmagineBusiness.getInstance().addImmagine(files.get(i), prodotto.getIdArticolo());
                                        if (!immagineResult.getResult().equals(ImmagineResult.Result.ADD_OK))
                                            break;
                                    }
                                } else JOptionPane.showMessageDialog(this.frame, immagineResult.getMessage());
                            } else CollocazioneBusiness.getInstance().removeCollocazione(collocazione);
                        } else CollocazioneBusiness.getInstance().removeCollocazione(collocazione);
                        JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                    } else JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                } else JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
            } else JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());
        } else JOptionPane.showMessageDialog(this.frame, puntoVenditaResult.getMessage());

        this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, prodotto, puntoVenditaResult.getPuntiVendita().get(0)));
    }
}
