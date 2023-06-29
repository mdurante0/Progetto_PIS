package View.Listener;

import Business.AbstractFactory.FactoryProvider;
import Business.*;
import Business.Results.*;
import Model.Collocazione;
import Model.PuntoVendita;
import Model.composite.ProdottoComposito;
import View.CatalogoProdottiPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CreaProdottoCompositoListener implements ActionListener {

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

    public CreaProdottoCompositoListener(MainFrame frame, JTextField nomeProdottoCompositoField, JTextField descrizioneField, JTextField quantitaField, JComboBox<String> categoriaProdottoBox, JComboBox<String> puntoVenditaBox, JTextField corsiaField, JTextField scaffaleField, ArrayList<File> files, ArrayList<JComboBox<String>> componentiBoxes, ArrayList<JTextField> quantitaComponentiFields) {
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

        ArticoloResult articoloResult;
        prodottoComposito = (ProdottoComposito) FactoryProvider.getFactory(FactoryProvider.FactoryType.PRODOTTO_COMPOSITO).crea();
        prodottoComposito.setName(nomeProdottoCompositoField.getText());
        prodottoComposito.setDescrizione(descrizioneField.getText());

        //Caricamento Categoria
        if(categoriaProdottoBox.getSelectedItem().toString().equals("Nessuna Categoria")) {
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategoriaProdottoByName(categoriaProdottoBox.getSelectedItem().toString());

            if (categoriaResult.getResult().equals(CategoriaResult.Result.CATEGORIE_CARICATE))
                prodottoComposito.setCategoria(categoriaResult.getCategorieProdotto().get(0));
            else {
                JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());
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

        //Inserimento Prodotto Composito
        articoloResult = ArticoloBusiness.getInstance().addArticolo(prodottoComposito);
        if (!articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)) {
            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
            return;
        }

        //Inserimento delle nuove immagini
        ImmagineResult immagineResult;
        for (File file : files) {
            immagineResult = ImmagineBusiness.getInstance().addImmagine(file, prodottoComposito.getIdArticolo());

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

                    //Inserimento Collocazione
                    Collocazione collocazione = new Collocazione(Integer.parseInt(corsiaField.getText()), Integer.parseInt(scaffaleField.getText()), magazzinoResult.getMagazzini().get(0));
                    CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().addCollocazione(collocazione);
                    if (collocazioneResult.getResult().equals(CollocazioneResult.Result.ADD_OK)) {
                        prodottoComposito.setCollocazione(collocazione);

                        //Inserimento Prodotto nel Magazzino
                        articoloResult = ArticoloBusiness.getInstance().addProdottoToMagazzino(prodottoComposito, magazzinoResult.getMagazzini().get(0).getIdMagazzino());
                        if (articoloResult.getResult().equals(ArticoloResult.Result.ADD_OK)) {
                            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
                            JOptionPane.showMessageDialog(this.frame, "Prodotto inserito con successo in " + puntoVendita.getNome());
                            this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, puntoVendita));

                        } else CollocazioneBusiness.getInstance().removeCollocazione(collocazione);

                    } else {
                        JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                        ArticoloBusiness.getInstance().removeArticolo(prodottoComposito);
                    }

                } else JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());

            } else JOptionPane.showMessageDialog(this.frame, puntoVenditaResult.getMessage());

        } else {
            JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());
            this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, null));
        }
    }
}
