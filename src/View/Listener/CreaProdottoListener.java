package View.Listener;

import Business.AbstractFactory.FactoryProvider;
import Business.*;
import Business.Results.*;
import Model.Collocazione;
import Model.PuntoVendita;
import Model.composite.Prodotto;
import View.CatalogoProdottiPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CreaProdottoListener implements ActionListener {
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

    public CreaProdottoListener(MainFrame frame, JTextField nomeProdottoField, JTextField descrizioneField, JTextField prezzoField, JTextField quantitaField, JComboBox<String> produttoreBox, JComboBox<String> categoriaProdottoBox, JComboBox<String> puntoVenditaBox, JTextField corsiaField, JTextField scaffaleField, ArrayList<File> files) {
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
        ArticoloResult articoloResult;
        prodotto = (Prodotto) FactoryProvider.getFactory(FactoryProvider.FactoryType.PRODOTTO).crea();
        prodotto.setName(nomeProdottoField.getText());
        prodotto.setDescrizione(descrizioneField.getText());

        try {
            prodotto.setPrezzo(Float.valueOf(prezzoField.getText()));
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
            return;
        }

        //Caricamento Produttore
        if (produttoreBox.getSelectedItem() != null && !produttoreBox.getSelectedItem().toString().isBlank()) {
            ProduttoreResult produttoreResult = ProduttoreBusiness.getInstance().caricaProduttoreByNome(produttoreBox.getSelectedItem().toString());

            if (produttoreResult.getResult().equals(ProduttoreResult.Result.PRODUTTORI_CARICATI))
                prodotto.setProduttore(produttoreResult.getProduttori().get(0));
            else JOptionPane.showMessageDialog(this.frame, produttoreResult.getMessage());
        }

        //Caricamento Categoria
        if (categoriaProdottoBox.getSelectedItem() != null && !categoriaProdottoBox.getSelectedItem().toString().isBlank()) {
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategoriaProdottoByName(categoriaProdottoBox.getSelectedItem().toString());

            if (categoriaResult.getResult().equals(CategoriaResult.Result.CATEGORIE_CARICATE))
                prodotto.setCategoria(categoriaResult.getCategorieProdotto().get(0));
            else {
                JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());
                return;
            }
        }

        //Inserimento Prodotto
        articoloResult = ArticoloBusiness.getInstance().addArticolo(prodotto);
        if (!articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)) {
            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
            return;
        }

        //Inserimento delle nuove immagini
        ImmagineResult immagineResult;
        for (File file : files) {
            immagineResult = ImmagineBusiness.getInstance().addImmagine(file, prodotto.getIdArticolo());

            if (!immagineResult.getResult().equals(ImmagineResult.Result.ADD_OK)) {
                JOptionPane.showMessageDialog(this.frame, immagineResult.getMessage());
                return;
            }
        }

        //Caricamento Punto Vendita
        PuntoVenditaResult puntoVenditaResult;
        if (!puntoVenditaBox.getSelectedItem().toString().equals("Nessun punto vendita")) {

            puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByNome(puntoVenditaBox.getSelectedItem().toString());
            if (puntoVenditaResult.getResult().equals(PuntoVenditaResult.Result.SALEPOINT_CARICATI)) {
                PuntoVendita puntoVendita = puntoVenditaResult.getPuntiVendita().get(0);

                //Caricamento Magazzino
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

                    //Inserimento Collocazione
                    Collocazione collocazione = new Collocazione(Integer.parseInt(corsiaField.getText()), Integer.parseInt(scaffaleField.getText()), magazzinoResult.getMagazzini().get(0));
                    CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().addCollocazione(collocazione);
                    if (collocazioneResult.getResult().equals(CollocazioneResult.Result.ADD_OK)) {
                        prodotto.setCollocazione(collocazione);

                        //Inserimento Prodotto nel Magazzino
                        articoloResult = ArticoloBusiness.getInstance().addProdottoToMagazzino(prodotto, magazzinoResult.getMagazzini().get(0).getIdMagazzino());
                        if (articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)) {
                            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                            JOptionPane.showMessageDialog(this.frame, "Prodotto inserito con successo in " + puntoVendita.getNome());
                            this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, puntoVendita));

                        } else CollocazioneBusiness.getInstance().removeCollocazione(collocazione);

                    } else {
                        JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                        ArticoloBusiness.getInstance().removeArticolo(prodotto);
                    }

                } else JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());

            } else JOptionPane.showMessageDialog(this.frame, puntoVenditaResult.getMessage());

        } else {
            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
            this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, null));
        }
    }
}
