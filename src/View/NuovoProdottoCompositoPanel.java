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
import View.Listener.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static View.Listener.ComponenteListener.AGGIUNGI;

public class NuovoProdottoCompositoPanel extends JPanel {
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

    public NuovoProdottoCompositoPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Nuovo prodotto composito");
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

        nomeProdottoCompositoField = new JTextField(20);
        descrizioneField = new JTextField(20);
        quantitaField = new JTextField(20);

        nomeProdottoCompositoField.setFont(bodyFont);
        descrizioneField.setFont(bodyFont);
        quantitaField.setFont(bodyFont);

        contentPanel.add(nomeProdottoLabel);
        contentPanel.add(nomeProdottoCompositoField);
        contentPanel.add(descrizioneLabel);
        contentPanel.add(descrizioneField);
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

            JLabel puntoVenditaLabel = new JLabel("  Punto vendita in cui vendere il nuovo prodotto:");
            puntoVenditaLabel.setFont(bodyFont);
            puntoVenditaBox.setFont(bodyFont);
            contentPanel.add(puntoVenditaLabel);
            contentPanel.add(puntoVenditaBox);

            JLabel corsiaLabel = new JLabel("  Corsia:");
            corsiaLabel.setFont(bodyFont);
            corsiaField = new JTextField(20);
            corsiaField.setFont(bodyFont);
            contentPanel.add(corsiaLabel);
            contentPanel.add(corsiaField);

            JLabel scaffaleLabel = new JLabel("  Scaffale:");
            scaffaleLabel.setFont(bodyFont);
            scaffaleField = new JTextField(20);
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

        JButton rimuoviImmagineButton = new JButton("Rimuovi l'ultima immagine");
        rimuoviImmagineButton.setFont(bodyFont);
        rimuoviImmagineButton.setActionCommand(ImmaginiListener.RIMUOVI);
        rimuoviImmagineButton.addActionListener(new ImmaginiListener(this.frame, files, immaginiCounterLabel));

        JLabel componente1 = new JLabel("  Componente 1:");
        componente1.setFont(bodyFont);
        JComboBox<String> componente1Box = new JComboBox<>();
        String[] nomiProdotti = new String[0];
        CatalogoResult catalogoResult = CatalogoBusiness.getInstance().caricaCatalogoProdotti();
        if(catalogoResult.getListaProdotti() != null) {
            Iterator<IProdotto> iterator = catalogoResult.getListaProdotti().iterator();
            nomiProdotti = new String[catalogoResult.getListaProdotti().size()];
            for (int i = 0; i < catalogoResult.getListaProdotti().size(); i++) {
                nomiProdotti[i] = iterator.next().getName();
            }
            componente1Box = new JComboBox<>(nomiProdotti);
            componente1Box.setFocusable(false);
            componente1Box.setFont(bodyFont);
        }
        componentiBoxes.add(componente1Box);

        JLabel quantita1 = new JLabel("  Quantità componente 1:");
        quantita1.setFont(bodyFont);
        JTextField quantita1Field = new JTextField(20);
        quantita1Field.setFont(bodyFont);
        quantitaComponentiFields.add(quantita1Field);

        JLabel componente2 = new JLabel("  Componente 2:");
        componente2.setFont(bodyFont);
        JComboBox<String> componente2Box = new JComboBox<>(nomiProdotti);
        componente2Box.setFocusable(false);
        componente2Box.setFont(bodyFont);
        componentiBoxes.add(componente2Box);

        JLabel quantita2 = new JLabel("  Quantità componente 2:");
        quantita2.setFont(bodyFont);
        JTextField quantita2Field = new JTextField(20);
        quantita2Field.setFont(bodyFont);
        quantitaComponentiFields.add(quantita2Field);

        JButton aggiungiComponenteButton = new JButton("Aggiunti componente");
        aggiungiComponenteButton.setFont(bodyFont);
        aggiungiComponenteButton.setActionCommand(AGGIUNGI);
        aggiungiComponenteButton.addActionListener(new ComponenteListener(this.contentPanel, aggiungiComponenteButton, componentiBoxes, quantitaComponentiFields));
        JButton backButton = new JButton("Torna indietro");
        backButton.setFont(bodyFont);
        backButton.addActionListener(new GoToMenuListener(this.frame));

        JButton aggiungiProdottoCompositoButton = new JButton("Aggiungi prodotto composito");
        aggiungiProdottoCompositoButton.setFont(bodyFont);
        aggiungiProdottoCompositoButton.addActionListener(new CreaNuovoProdottoCompositoListener(this.frame, nomeProdottoCompositoField, descrizioneField, quantitaField, categoriaProdottoBox, puntoVenditaBox, corsiaField, scaffaleField, files, componentiBoxes, quantitaComponentiFields));


        contentPanel.add(immaginiLabel);
        contentPanel.add(aggiungiImmagineButton);
        contentPanel.add(immaginiCounterLabel);
        contentPanel.add(rimuoviImmagineButton);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(componente1);
        contentPanel.add(componente1Box);
        contentPanel.add(quantita1);
        contentPanel.add(quantita1Field);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(componente2);
        contentPanel.add(componente2Box);
        contentPanel.add(quantita2);
        contentPanel.add(quantita2Field);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(aggiungiComponenteButton);

        southPanel.add(backButton);
        southPanel.add(aggiungiProdottoCompositoButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}