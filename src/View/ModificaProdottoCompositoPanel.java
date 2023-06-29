package View;

import Business.CatalogoBusiness;
import Business.CategoriaBusiness;
import Business.CollocazioneBusiness;
import Business.PuntoVenditaBusiness;
import Business.Results.CatalogoResult;
import Business.Results.CategoriaResult;
import Business.Results.CollocazioneResult;
import Business.Results.PuntoVenditaResult;
import Model.CategoriaProdotto;
import Model.Immagine;
import Model.PuntoVendita;
import Model.composite.IProdotto;
import Model.composite.ProdottoComposito;
import View.Listener.ComponenteListener;
import View.Listener.GoToDettagliListener;
import View.Listener.ImmaginiListener;
import View.Listener.ModificaProdottoCompositoListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static View.Listener.ComponenteListener.AGGIUNGI;
import static View.Listener.ComponenteListener.RIMUOVI;

public class ModificaProdottoCompositoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JTextField nomeProdottoCompositoField;
    private JTextField descrizioneField;
    private JTextField quantitaField;
    private JComboBox<String> categoriaProdottoBox;
    private JComboBox<String> puntoVenditaBox;
    private JTextField corsiaField;
    private JTextField scaffaleField;
    private ArrayList<File> files = new ArrayList<>();
    private ArrayList<JComboBox<String>> componentiBoxes = new ArrayList<>();
    private ArrayList<JTextField> quantitaComponentiFields = new ArrayList<>();
    private JLabel immaginiCounterLabel;

    public ModificaProdottoCompositoPanel(MainFrame frame, ProdottoComposito prodottoComposito, PuntoVendita puntoVendita) {
        this.frame = frame;

        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Modifica prodotto composito");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 50, 300);
        this.add(scrollPane);

        contentPanel.setLayout(new GridLayout(0, 2));
        this.setPreferredSize(new Dimension(500, 400));
        JLabel nomeProdottoLabel = new JLabel("  Nome:");
        JLabel descrizioneLabel = new JLabel("  Descrizione:");
        JLabel quantitaLabel = new JLabel("  Quantità:");


        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 22);
        nomeProdottoLabel.setFont(bodyFont);
        descrizioneLabel.setFont(bodyFont);
        quantitaLabel.setFont(bodyFont);

        nomeProdottoCompositoField = new JTextField(prodottoComposito.getName(),20);
        descrizioneField = new JTextField(prodottoComposito.getDescrizione(),20);
        quantitaField = new JTextField(String.valueOf(prodottoComposito.getQuantita()),20);

        nomeProdottoCompositoField.setFont(bodyFont);
        descrizioneField.setFont(bodyFont);
        quantitaField.setFont(bodyFont);

        contentPanel.add(nomeProdottoLabel);
        contentPanel.add(nomeProdottoCompositoField);
        contentPanel.add(descrizioneLabel);
        contentPanel.add(descrizioneField);

        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntiVendita();
        CollocazioneResult collocazioneResult;
        if (puntoVenditaResult.getPuntiVendita() != null) {
            Iterator<PuntoVendita> iterator = puntoVenditaResult.getPuntiVendita().iterator();
            String[] nomiPV = new String[puntoVenditaResult.getPuntiVendita().size() + 1];
            for (int i = 0; i < puntoVenditaResult.getPuntiVendita().size(); i++) {
                nomiPV[i] = iterator.next().getNome();
            }
            nomiPV[nomiPV.length - 1] = "Nessun punto vendita";
            puntoVenditaBox = new JComboBox<>(nomiPV);
            puntoVenditaBox.setFocusable(false);
            puntoVenditaBox.setFont(bodyFont);

            if(puntoVendita != null) {
                puntoVenditaBox.setSelectedItem(puntoVendita.getNome());
                prodottoComposito.setMagazzino(puntoVendita.getMagazzino());
                quantitaField = new JTextField(String.valueOf(prodottoComposito.getQuantita()),20);
                collocazioneResult = CollocazioneBusiness.getInstance().caricaCollocazioneById(prodottoComposito.getCollocazione().getIdCollocazione());
                prodottoComposito.setCollocazione(collocazioneResult.getCollocazioni().get(0));
                corsiaField = new JTextField(String.valueOf(prodottoComposito.getCollocazione().getCorsia()),20);
                scaffaleField = new JTextField(String.valueOf(prodottoComposito.getCollocazione().getScaffale()),20);
            } else {
                puntoVenditaBox.setSelectedItem("Nessun punto vendita");
                quantitaField = new JTextField(20);
                corsiaField = new JTextField(20);
                scaffaleField = new JTextField(20);
            }
            JLabel puntoVenditaLabel = new JLabel("  Punto vendita in cui vendere il prodotto:");
            puntoVenditaLabel.setFont(bodyFont);
            puntoVenditaBox.setFont(bodyFont);
            quantitaField.setFont(bodyFont);
            contentPanel.add(quantitaLabel);
            contentPanel.add(quantitaField);
            contentPanel.add(puntoVenditaLabel);
            contentPanel.add(puntoVenditaBox);

            JLabel corsiaLabel = new JLabel("  Corsia:");
            corsiaLabel.setFont(bodyFont);
            corsiaField.setFont(bodyFont);
            contentPanel.add(corsiaLabel);
            contentPanel.add(corsiaField);

            JLabel scaffaleLabel = new JLabel("  Scaffale:");
            scaffaleLabel.setFont(bodyFont);
            scaffaleField.setFont(bodyFont);
            contentPanel.add(scaffaleLabel);
            contentPanel.add(scaffaleField);
        }

        CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategorieProdotto();
        if(categoriaResult.getCategorieProdotto() != null) {
            Iterator<CategoriaProdotto> iterator = categoriaResult.getCategorieProdotto().iterator();
            String[] nomiCategorieProdotto = new String[categoriaResult.getCategorieProdotto().size() + 1];
            for (int i = 0; i < categoriaResult.getCategorieProdotto().size(); i++) {
                nomiCategorieProdotto[i] = iterator.next().getNome();
            }
            nomiCategorieProdotto[nomiCategorieProdotto.length - 1] = "Nessuna Categoria";
            categoriaProdottoBox = new JComboBox<>(nomiCategorieProdotto);
            categoriaProdottoBox.setFocusable(false);
            categoriaProdottoBox.setFont(bodyFont);
            if (prodottoComposito.getCategoria().getNome() != null)
                categoriaProdottoBox.setSelectedItem(prodottoComposito.getCategoria().getNome());
            else categoriaProdottoBox.setSelectedItem("Nessuna Categoria");

            JLabel categoriaLabel = new JLabel("  Categoria da assegnare al prodotto composito:");
            categoriaLabel.setFont(bodyFont);
            categoriaProdottoBox.setFont(bodyFont);
            contentPanel.add(categoriaLabel);
            contentPanel.add(categoriaProdottoBox);
        }

        for (Immagine immagine :
                prodottoComposito.getImmagini()) {
            files.add(immagine.getFile());
        }
        JLabel immaginiLabel = new JLabel("  Aggiungi le immagini:");
        immaginiLabel.setFont(bodyFont);

        immaginiCounterLabel = new JLabel("  Immagini inserite: " + files.size());
        immaginiCounterLabel.setFont(bodyFont);

        JButton aggiungiImmagineButton = new JButton("Aggiungi immagine");
        aggiungiImmagineButton.setFont(bodyFont);
        aggiungiImmagineButton.setActionCommand(ImmaginiListener.AGGIUNGI);
        aggiungiImmagineButton.addActionListener(new ImmaginiListener(this.frame, files, immaginiCounterLabel));

        JButton rimuoviImmagineButton = new JButton("Rimuovi l'ultima immagine");
        rimuoviImmagineButton.setFont(bodyFont);
        rimuoviImmagineButton.setActionCommand(ImmaginiListener.RIMUOVI);
        rimuoviImmagineButton.addActionListener(new ImmaginiListener(this.frame, files, immaginiCounterLabel));

        contentPanel.add(immaginiLabel);
        contentPanel.add(aggiungiImmagineButton);
        contentPanel.add(immaginiCounterLabel);
        contentPanel.add(rimuoviImmagineButton);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());

        String[] nomiProdotti;
        CatalogoResult catalogoResult = CatalogoBusiness.getInstance().caricaCatalogoProdotti();
        if(catalogoResult.getListaProdotti() != null) {
            Iterator<IProdotto> iterator = catalogoResult.getListaProdotti().iterator();
            nomiProdotti = new String[catalogoResult.getListaProdotti().size()];
            for (int i = 0; i < catalogoResult.getListaProdotti().size(); i++) {
                nomiProdotti[i] = iterator.next().getName();
            }
            for (int i = 0; i < prodottoComposito.getSottoprodotti().size(); i++) {
                JLabel componenteI = new JLabel("  Componente " + (i + 1) + ":");
                componenteI.setFont(bodyFont);
                JComboBox<String> componenteIBox = new JComboBox<>(nomiProdotti);
                componenteIBox.setFocusable(false);
                componenteIBox.setFont(bodyFont);
                componenteIBox.setSelectedItem(prodottoComposito.getSottoprodotti().get(i).getName());
                componentiBoxes.add(componenteIBox);

                JLabel quantitaI = new JLabel("  Quantità componente " + (i + 1) + ":");
                quantitaI.setFont(bodyFont);
                JTextField quantitaIField = new JTextField(String.valueOf(prodottoComposito.getSottoprodotti().get(i).getQuantita()),20);
                quantitaIField.setFont(bodyFont);
                quantitaComponentiFields.add(quantitaIField);

                contentPanel.add(componenteI);
                contentPanel.add(componenteIBox);
                contentPanel.add(quantitaI);
                contentPanel.add(quantitaIField);
                contentPanel.add(new JLabel());
                contentPanel.add(new JLabel());
            }
        }
        ComponenteListener.setComponentsCounter(prodottoComposito.getSottoprodotti().size());
        JButton aggiungiComponenteButton = new JButton("Aggiunti componente");
        aggiungiComponenteButton.setFont(bodyFont);
        aggiungiComponenteButton.setActionCommand(AGGIUNGI);
        aggiungiComponenteButton.addActionListener(new ComponenteListener(this.contentPanel, aggiungiComponenteButton, componentiBoxes, quantitaComponentiFields));
        JButton backButton = new JButton("Torna indietro");
        backButton.setFont(bodyFont);
        backButton.addActionListener(new GoToDettagliListener(this.frame, prodottoComposito, puntoVendita));

        JButton rimuoviComponenteButton = new JButton("Rimuovi componente");
        rimuoviComponenteButton.setFont(bodyFont);
        rimuoviComponenteButton.setActionCommand(RIMUOVI);
        rimuoviComponenteButton.addActionListener(new ComponenteListener(this.contentPanel, aggiungiComponenteButton, componentiBoxes, quantitaComponentiFields));

        JButton modificaProdottoCompositoButton = new JButton("Modifica prodotto composito");
        modificaProdottoCompositoButton.setFont(bodyFont);
        modificaProdottoCompositoButton.addActionListener(new ModificaProdottoCompositoListener(this.frame, nomeProdottoCompositoField, descrizioneField, quantitaField, categoriaProdottoBox, puntoVenditaBox, corsiaField, scaffaleField, files, componentiBoxes, quantitaComponentiFields, prodottoComposito));

        if(prodottoComposito.getSottoprodotti().size() > 2)
            contentPanel.add(rimuoviComponenteButton);
        else
            contentPanel.add(new JLabel());
        contentPanel.add(aggiungiComponenteButton);

        southPanel.add(backButton);
        southPanel.add(modificaProdottoCompositoButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}