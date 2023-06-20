package View;

import Business.CatalogoBusiness;
import Business.CategoriaBusiness;
import Business.PuntoVenditaBusiness;
import Business.Results.CatalogoResult;
import Business.Results.CategoriaResult;
import Business.Results.PuntoVenditaResult;
import Model.CategoriaProdotto;
import Model.PuntoVendita;
import Model.composite.IProdotto;
import Model.composite.ProdottoComposito;
import View.Listener.ComponenteListener;
import View.Listener.GoToMenuListener;
import View.Listener.ImmaginiListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class ModificaProdottoCompositoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JTextField nomeProdottoField;
    private JTextField descrizioneField;
    private JTextField prezzoField;
    private JTextField quantitaField;
    private JComboBox<String> categoriaProdottoBox;
    private JComboBox<String> puntoVenditaBox;
    private JTextField corsiaField;
    private JTextField scaffaleField;
    private ArrayList<File> files = new ArrayList<>();
    private JLabel immaginiCounterLabel;
    private int componentsCounter = 2;

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
        JLabel prezzoLabel = new JLabel("  Prezzo (€):");
        JLabel quantitaLabel = new JLabel("  Quantità:");


        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 22);
        nomeProdottoLabel.setFont(bodyFont);
        descrizioneLabel.setFont(bodyFont);
        prezzoLabel.setFont(bodyFont);
        quantitaLabel.setFont(bodyFont);

        nomeProdottoField = new JTextField(prodottoComposito.getName(),20);
        descrizioneField = new JTextField(prodottoComposito.getDescrizione(),20);
        prezzoField = new JTextField(String.valueOf(prodottoComposito.getPrezzo()),20);
        quantitaField = new JTextField(String.valueOf(prodottoComposito.getQuantita()),20);

        nomeProdottoField.setFont(bodyFont);
        descrizioneField.setFont(bodyFont);
        prezzoField.setFont(bodyFont);
        quantitaField.setFont(bodyFont);

        contentPanel.add(nomeProdottoLabel);
        contentPanel.add(nomeProdottoField);
        contentPanel.add(descrizioneLabel);
        contentPanel.add(descrizioneField);
        contentPanel.add(prezzoLabel);
        contentPanel.add(prezzoField);
        contentPanel.add(quantitaLabel);
        contentPanel.add(quantitaField);

        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntiVendita();
        if (puntoVenditaResult.getPuntiVendita() != null) {
            Iterator<PuntoVendita> iterator = puntoVenditaResult.getPuntiVendita().iterator();
            String[] nomiPV = new String[puntoVenditaResult.getPuntiVendita().size()];
            for (int i = 0; i < puntoVenditaResult.getPuntiVendita().size(); i++) {
                nomiPV[i] = iterator.next().getNome();
            }
            puntoVenditaBox = new JComboBox<>(nomiPV);
            puntoVenditaBox.setFocusable(false);
            puntoVenditaBox.setFont(bodyFont);
            puntoVenditaBox.setSelectedItem(puntoVendita.getNome());

            JLabel puntoVenditaLabel = new JLabel("  Punto vendita in cui vendere il nuovo prodotto:");
            puntoVenditaLabel.setFont(bodyFont);
            puntoVenditaBox.setFont(bodyFont);
            contentPanel.add(puntoVenditaLabel);
            contentPanel.add(puntoVenditaBox);

            JLabel corsiaLabel = new JLabel("  Corsia:");
            corsiaLabel.setFont(bodyFont);
            corsiaField = new JTextField(String.valueOf(prodottoComposito.getCollocazione().getCorsia()),20);
            corsiaField.setFont(bodyFont);
            contentPanel.add(corsiaLabel);
            contentPanel.add(corsiaField);

            JLabel scaffaleLabel = new JLabel("  Scaffale:");
            scaffaleLabel.setFont(bodyFont);
            scaffaleField = new JTextField(String.valueOf(prodottoComposito.getCollocazione().getScaffale()),20);
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
            categoriaProdottoBox = new JComboBox<>(nomiCategorieProdotto);
            categoriaProdottoBox.setFocusable(false);
            categoriaProdottoBox.setFont(bodyFont);
            categoriaProdottoBox.setSelectedItem(prodottoComposito.getCategoria().getNome());

            JLabel categoriaLabel = new JLabel("  Categoria da assegnare al nuovo prodotto:");
            categoriaLabel.setFont(bodyFont);
            categoriaProdottoBox.setFont(bodyFont);
            contentPanel.add(categoriaLabel);
            contentPanel.add(categoriaProdottoBox);
        }

        JLabel immaginiLabel = new JLabel("  Aggiungi le immagini:");
        immaginiLabel.setFont(bodyFont);

        immaginiCounterLabel = new JLabel("  Immagini inserite: " + files.size());
        immaginiCounterLabel.setFont(bodyFont);

        JButton aggiungiImmagineButton = new JButton("Aggiungi immagine");
        aggiungiImmagineButton.setFont(bodyFont);
        aggiungiImmagineButton.setActionCommand(ImmaginiListener.AGGIUNGI);
        aggiungiImmagineButton.addActionListener(new ImmaginiListener(this.frame, files, immaginiCounterLabel));

        contentPanel.add(immaginiLabel);
        contentPanel.add(aggiungiImmagineButton);

        JButton rimuoviImmagineButton = new JButton("Rimuovi l'ultima immagine");
        rimuoviImmagineButton.setFont(bodyFont);
        rimuoviImmagineButton.setActionCommand(ImmaginiListener.RIMUOVI);
        rimuoviImmagineButton.addActionListener(new ImmaginiListener(this.frame, files, immaginiCounterLabel));

        contentPanel.add(immaginiCounterLabel);
        contentPanel.add(rimuoviImmagineButton);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());

        String[] nomiProdotti ;
        CatalogoResult catalogoResult = CatalogoBusiness.getInstance().caricaCatalogoProdotti();
        for( int j=0; j < prodottoComposito.getSottoprodotti().size(); j++) {
            JLabel componente = new JLabel("  Componente:");
            componente.setFont(bodyFont);
            JComboBox<String> componenteBox = new JComboBox<>();
            Iterator<IProdotto> iterator = catalogoResult.getListaProdotti().iterator();
            nomiProdotti = new String[catalogoResult.getListaProdotti().size()];
            for (int i = 0; i < catalogoResult.getListaProdotti().size(); i++) {
                nomiProdotti[i] = iterator.next().getName();
            }
            componenteBox = new JComboBox<>(nomiProdotti);
            componenteBox.setFocusable(false);
            componenteBox.setFont(bodyFont);
            componenteBox.setSelectedItem(prodottoComposito.getSottoprodotti().get(j).getName());

            contentPanel.add(componente);
            contentPanel.add(componenteBox);

            JLabel quantita1 = new JLabel("  Quantità componente :");
            quantita1.setFont(bodyFont);
            JTextField quantita1Field = new JTextField(String.valueOf(prodottoComposito.getSottoprodotti().get(0).getQuantita()),20);
            quantita1Field.setFont(bodyFont);
            contentPanel.add(quantita1);
            contentPanel.add(quantita1Field);
            contentPanel.add(new JLabel());
            contentPanel.add(new JLabel());
        }

        JButton aggiungiComponenteButton = new JButton("Aggiunti componente");
        aggiungiComponenteButton.setFont(bodyFont);
        aggiungiComponenteButton.setActionCommand(ComponenteListener.AGGIUNGI);
        //aggiungiComponenteButton.addActionListener(new ComponenteListener(this.contentPanel, aggiungiComponenteButton));

        JButton backButton = new JButton("Torna indietro");
        backButton.setFont(bodyFont);
        backButton.addActionListener(new GoToMenuListener(this.frame));

        JButton aggiungiProdottoCompositoButton = new JButton("Modifica prodotto composito");
        aggiungiProdottoCompositoButton.setFont(bodyFont);
        //aggiungiProdottoCompositoButton.addActionListener();

        contentPanel.add(new JLabel());
        contentPanel.add(aggiungiComponenteButton);

        southPanel.add(backButton);
        southPanel.add(aggiungiProdottoCompositoButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        //this.add(contentPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}