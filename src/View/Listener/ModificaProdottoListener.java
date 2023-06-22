package View.Listener;

import Business.*;
import Business.Results.*;
import Model.PuntoVendita;
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
        try {
            Float.parseFloat(prezzoField.getText());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
            return;
        }

        ArticoloResult articoloResult;
        prodotto.setName(nomeProdottoField.getText());
        prodotto.setDescrizione(descrizioneField.getText());
        prodotto.setPrezzo(Float.valueOf(prezzoField.getText()));


        //Caricamento Produttore
        if (produttoreBox.getSelectedItem() != null && !produttoreBox.getSelectedItem().toString().isBlank()) {
            ProduttoreResult produttoreResult = ProduttoreBusiness.getInstance().caricaProduttoreByNome(produttoreBox.getSelectedItem().toString());

            if (produttoreResult.getResult().equals(ProduttoreResult.Result.PRODUTTORI_CARICATI))
                prodotto.setProduttore(produttoreResult.getProduttori().get(0));
            else {
                JOptionPane.showMessageDialog(this.frame, produttoreResult.getMessage());
                return;
            }
        }

        //Caricamento Categoria
        if (categoriaProdottoBox.getSelectedItem() != null && !categoriaProdottoBox.getSelectedItem().toString().isBlank()) {
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategoriaProdottoByName(categoriaProdottoBox.getSelectedItem().toString());

            if (categoriaResult.getResult().equals(CategoriaResult.Result.CATEGORIE_CARICATE))
                prodotto.setCategoria(categoriaResult.getCategorieProdotto().get(0));
            else{
                JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());
                return;
            }

        } else if (prodotto.getCategoria().getNome() != null) {
            articoloResult = ArticoloBusiness.getInstance().removeArticoloFromCategoria(prodotto);

            if (!articoloResult.getResult().equals(ArticoloResult.Result.DELETE_OK)) {
                JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                return;
            }
        }

        //Aggiornamento Prodotto
        articoloResult = ArticoloBusiness.getInstance().updateArticolo(prodotto);
        if (!articoloResult.getResult().equals(ArticoloResult.Result.UPDATE_OK)) {
            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
            return;
        }

        //Rimozione vecchie Immagini
        ImmagineResult immagineResult;
        if (prodotto.getImmagini().size() != 0) {
            immagineResult = ImmagineBusiness.getInstance().removeImmagineByArticolo(prodotto);
            if (!immagineResult.getResult().equals(ImmagineResult.Result.REMOVE_OK)) {
                JOptionPane.showMessageDialog(this.frame, immagineResult.getMessage());
                return;
            }
        }
        //Inserimento delle nuove immagini
        for (File file : files) {
            immagineResult = ImmagineBusiness.getInstance().addImmagine(file, prodotto.getIdArticolo());
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
                if(prodotto.getMagazzino().getIndirizzo() != null) {
                    articoloResult = ArticoloBusiness.getInstance().removeProdottoFromMagazzino(prodotto, prodotto.getMagazzino().getIdMagazzino());
                    if (!articoloResult.getResult().equals(ArticoloResult.Result.DELETE_OK)) {
                        JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                        return;
                    }
                }
                //Caricamento nuovo Magazzino
                MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByPuntoVendita(puntoVendita);
                prodotto.setMagazzino(magazzinoResult.getMagazzini().get(0));
                if (magazzinoResult.getResult().equals(MagazzinoResult.Result.MAGAZZINI_CARICATI)) {

                    try {
                        Integer.parseInt(quantitaField.getText());
                        Integer.parseInt(corsiaField.getText());
                        Integer.parseInt(scaffaleField.getText());
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
                        return;
                    }
                    prodotto.setQuantita(Integer.parseInt(quantitaField.getText()));

                    //Aggiornamento collocazione
                    CollocazioneResult collocazioneResult;
                    prodotto.getCollocazione().setIdProdotto(prodotto.getIdArticolo());
                    prodotto.getCollocazione().setCorsia(Integer.parseInt(corsiaField.getText()));
                    prodotto.getCollocazione().setScaffale(Integer.parseInt(scaffaleField.getText()));

                    if(prodotto.getCollocazione().getMagazzino() == null){
                        prodotto.getCollocazione().setMagazzino(magazzinoResult.getMagazzini().get(0));
                        collocazioneResult = CollocazioneBusiness.getInstance().addCollocazione(prodotto.getCollocazione());
                    }else {
                        prodotto.getCollocazione().setMagazzino(magazzinoResult.getMagazzini().get(0));
                        collocazioneResult = CollocazioneBusiness.getInstance().updateCollocazione(prodotto.getCollocazione());
                    }
                    if (collocazioneResult.getResult().equals(CollocazioneResult.Result.UPDATE_OK) || collocazioneResult.getResult().equals(CollocazioneResult.Result.ADD_OK)) {

                        //Inserimento Prodotto nel nuovo Magazzino (collocazione e quantità)
                        articoloResult = ArticoloBusiness.getInstance().addProdottoToMagazzino(prodotto, magazzinoResult.getMagazzini().get(0).getIdMagazzino());
                        if (!articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)) {
                            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                            collocazioneResult = CollocazioneBusiness.getInstance().removeCollocazione(prodotto.getCollocazione());
                            if(!collocazioneResult.getResult().equals(CollocazioneResult.Result.DELETE_OK)){
                                JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(this.frame, "Prodotto inserito con successo in " + puntoVendita.getNome());
                            puntoVendita.getMagazzino().add(prodotto);
                            this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, prodotto, puntoVendita));
                        }
                    } else JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                } else JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());
            } else JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());

        } else if (prodotto.getMagazzino().getIndirizzo() != null){

            //Rimozione dal magazzino
            articoloResult = ArticoloBusiness.getInstance().removeProdottoFromMagazzino(prodotto, prodotto.getMagazzino().getIdMagazzino());
            if (!articoloResult.getResult().equals(ArticoloResult.Result.DELETE_OK)) {
                JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                return;
            }
            CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().removeCollocazione(prodotto.getCollocazione());
            if(!collocazioneResult.getResult().equals(CollocazioneResult.Result.DELETE_OK)){
                JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                return;
            }
            this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, prodotto, null));

        }else this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, prodotto, null));

        JOptionPane.showMessageDialog(this.frame, "Prodotto modificato con successo");
    }
}
