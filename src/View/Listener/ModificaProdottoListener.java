package View.Listener;

import Business.*;
import Business.Results.*;
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
        } else if (prodotto.getCategoria().getNome() != null){
            articoloResult = ArticoloBusiness.getInstance().removeArticoloFromCategoria(prodotto);
            if(!articoloResult.getResult().equals(ArticoloResult.Result.DELETE_OK)){
                JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                return;
            }
        }

        //Caricamento Punto Vendita
        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByNome(puntoVenditaBox.getSelectedItem().toString());
        if (puntoVenditaResult.getResult().equals(PuntoVenditaResult.Result.SALEPOINT_CARICATI)) {

            //Rimozione dal vecchio magazzino
            articoloResult = ArticoloBusiness.getInstance().removeProdottoFromMagazzino(prodotto, prodotto.getMagazzino().getIdMagazzino());
            if(articoloResult.getResult().equals(ArticoloResult.Result.DELETE_OK)) {

                //Caricamento nuovo Magazzino
                MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByPuntoVendita(puntoVenditaResult.getPuntiVendita().get(0));
                prodotto.setMagazzino(magazzinoResult.getMagazzini().get(0));
                if (magazzinoResult.getResult().equals(MagazzinoResult.Result.MAGAZZINI_CARICATI)) {

                    //Aggiornamento nuova collocazione
                    prodotto.getCollocazione().setIdProdotto(prodotto.getIdArticolo());
                    prodotto.getCollocazione().setCorsia(Integer.parseInt(corsiaField.getText()));
                    prodotto.getCollocazione().setScaffale(Integer.parseInt(scaffaleField.getText()));
                    prodotto.getCollocazione().setMagazzino(magazzinoResult.getMagazzini().get(0));
                    CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().updateCollocazione(prodotto.getCollocazione());
                    if (collocazioneResult.getResult().equals(CollocazioneResult.Result.UPDATE_OK)) {

                        //Aggiornamento Prodotto
                        articoloResult = ArticoloBusiness.getInstance().updateArticolo(prodotto);
                        if (articoloResult.getResult().equals(ArticoloResult.Result.UPDATE_OK)) {

                            //Inserimento Prodotto nel nuovo Magazzino (collocazione e quantità)
                            articoloResult = ArticoloBusiness.getInstance().addProdottoToMagazzino(prodotto, magazzinoResult.getMagazzini().get(0).getIdMagazzino());
                            if (articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)) {

                                //Rimozione vecchie Immagini
                                ImmagineResult immagineResult;
                                if(prodotto.getImmagini().size() != 0) {
                                    immagineResult = ImmagineBusiness.getInstance().removeImmagineByArticolo(prodotto);
                                    if (!immagineResult.getResult().equals(ImmagineResult.Result.REMOVE_OK)){
                                        JOptionPane.showMessageDialog(this.frame, immagineResult.getMessage());
                                        return;
                                    }
                                }

                                //Inserimento delle nuove immagini
                                for (int i = 0; i < files.size(); i++) {
                                    immagineResult = ImmagineBusiness.getInstance().addImmagine(files.get(i), prodotto.getIdArticolo());
                                    if (!immagineResult.getResult().equals(ImmagineResult.Result.ADD_OK))
                                        break;
                                }
                            }
                        }
                    } else JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                } else JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());
            } else JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
        } else JOptionPane.showMessageDialog(this.frame, puntoVenditaResult.getMessage());

        JOptionPane.showMessageDialog(this.frame, "Prodotto modificato con successo");
        this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, prodotto, puntoVenditaResult.getPuntiVendita().get(0)));
    }
}
