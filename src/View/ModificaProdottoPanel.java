package View;

import Business.CategoriaBusiness;
import Business.CollocazioneBusiness;
import Business.ProduttoreBusiness;
import Business.PuntoVenditaBusiness;
import Business.Results.CategoriaResult;
import Business.Results.CollocazioneResult;
import Business.Results.ProduttoreResult;
import Business.Results.PuntoVenditaResult;
import Model.CategoriaProdotto;
import Model.Immagine;
import Model.Produttore;
import Model.PuntoVendita;
import Model.composite.Prodotto;
import View.Listener.GoToDettagliListener;
import View.Listener.ImmaginiListener;
import View.Listener.ModificaProdottoListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class ModificaProdottoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nomeProdottoField;
    private JTextField descrizioneField;
    private JTextField prezzoField;
    private JTextField quantitaField;
    private JComboBox<String> produttoreBox;
    private JComboBox<String> categoriaProdottoBox;
    private JComboBox<String> puntoVenditaBox;
    private JTextField corsiaField;
    private JTextField scaffaleField;
    private ArrayList<File> files = new ArrayList<>();
    private JLabel immaginiCounterLabel;

    public ModificaProdottoPanel(MainFrame frame, Prodotto prodotto, PuntoVendita puntoVendita) {
        this.frame = frame;

        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Modifica prodotto");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(13, 1));
        JLabel nomeProdottoLabel = new JLabel("  Nome:");
        JLabel descrizioneLabel = new JLabel("  Descrizione:");
        JLabel prezzoLabel = new JLabel("  Prezzo (€):");
        JLabel quantitaLabel = new JLabel("  Quantità:");


        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        nomeProdottoLabel.setFont(bodyFont);
        descrizioneLabel.setFont(bodyFont);
        prezzoLabel.setFont(bodyFont);
        quantitaLabel.setFont(bodyFont);

        nomeProdottoField = new JTextField(prodotto.getName(),20);
        descrizioneField = new JTextField(prodotto.getDescrizione(), 20);
        prezzoField = new JTextField(String.valueOf(prodotto.getPrezzo()),20);


        nomeProdottoField.setFont(bodyFont);
        descrizioneField.setFont(bodyFont);
        prezzoField.setFont(bodyFont);


        contentPanel.add(nomeProdottoLabel);
        contentPanel.add(nomeProdottoField);
        contentPanel.add(descrizioneLabel);
        contentPanel.add(descrizioneField);
        contentPanel.add(prezzoLabel);
        contentPanel.add(prezzoField);

        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntiVendita();
        CollocazioneResult collocazioneResult;
        if (puntoVenditaResult.getPuntiVendita() != null) {
            Iterator<PuntoVendita> iterator = puntoVenditaResult.getPuntiVendita().iterator();
            String[] nomiPV = new String[puntoVenditaResult.getPuntiVendita().size() + 1];
            for (int i = 0; i < puntoVenditaResult.getPuntiVendita().size(); i++) {
                nomiPV[i] = iterator.next().getNome();
            }
            puntoVenditaBox = new JComboBox<>(nomiPV);
            puntoVenditaBox.setFocusable(false);
            puntoVenditaBox.setFont(bodyFont);

            if(puntoVendita != null) {
                puntoVenditaBox.setSelectedItem(puntoVendita.getNome());
                prodotto.setMagazzino(puntoVendita.getMagazzino());
                quantitaField = new JTextField(String.valueOf(prodotto.getQuantita()),20);
                collocazioneResult = CollocazioneBusiness.getInstance().caricaCollocazioneById(prodotto.getCollocazione().getIdCollocazione());
                prodotto.setCollocazione(collocazioneResult.getCollocazioni().get(0));
                corsiaField = new JTextField(String.valueOf(prodotto.getCollocazione().getCorsia()),20);
                scaffaleField = new JTextField(String.valueOf(prodotto.getCollocazione().getScaffale()),20);
            } else {
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

        ProduttoreResult produttoreResult = ProduttoreBusiness.getInstance().caricaProduttori();
        if (produttoreResult.getProduttori() != null){
            Iterator<Produttore> iterator = produttoreResult.getProduttori().iterator();
            String[] nomiProduttori = new String[produttoreResult.getProduttori().size()];
            for (int i = 0; i < produttoreResult.getProduttori().size(); i++) {
                nomiProduttori[i] = iterator.next().getNome();
            }
            produttoreBox = new JComboBox<>(nomiProduttori);
            produttoreBox.setFocusable(false);
            produttoreBox.setFont(bodyFont);
            produttoreBox.setSelectedItem(prodotto.getProduttore().getNome());

            JLabel produttoreLabel = new JLabel("  Produttore del prodotto:");
            produttoreLabel.setFont(bodyFont);
            produttoreBox.setFont(bodyFont);
            contentPanel.add(produttoreLabel);
            contentPanel.add(produttoreBox);
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
            categoriaProdottoBox.setSelectedItem(prodotto.getCategoria().getNome());

            JLabel categoriaLabel = new JLabel("  Categoria da assegnare al prodotto:");
            categoriaLabel.setFont(bodyFont);
            categoriaProdottoBox.setFont(bodyFont);
            contentPanel.add(categoriaLabel);
            contentPanel.add(categoriaProdottoBox);
        }

        for (Immagine immagine :
                prodotto.getImmagini()) {
            files.add(immagine.getFile());
        }
        JLabel immaginiLabel = new JLabel("  Aggiungi le immagini:");
        immaginiLabel.setFont(bodyFont);

        immaginiCounterLabel = new JLabel("  Immagini inserite: " + prodotto.getImmagini().size());
        immaginiCounterLabel.setFont(bodyFont);

        JButton aggiungiImmagineButton = new JButton("Aggiungi immagine");
        aggiungiImmagineButton.setFont(bodyFont);
        aggiungiImmagineButton.setActionCommand(ImmaginiListener.AGGIUNGI);
        aggiungiImmagineButton.addActionListener(new ImmaginiListener(this.frame, files, immaginiCounterLabel));

        JButton rimuoviImmagineButton = new JButton("Rimuovi l'ultima immagine");
        rimuoviImmagineButton.setFont(bodyFont);
        rimuoviImmagineButton.setActionCommand(ImmaginiListener.RIMUOVI);
        rimuoviImmagineButton.addActionListener(new ImmaginiListener(this.frame, files, immaginiCounterLabel));

        JButton aggiungiButton = new JButton("Modifica prodotto");
        aggiungiButton.setFont(bodyFont);
        aggiungiButton.addActionListener(new ModificaProdottoListener(this.frame, nomeProdottoField, descrizioneField, prezzoField, quantitaField, produttoreBox, categoriaProdottoBox, puntoVenditaBox, corsiaField, scaffaleField, files, prodotto));

        JButton backButton = new JButton("Torna indietro");
        backButton.setFont(bodyFont);
        backButton.addActionListener(new GoToDettagliListener(this.frame, prodotto, puntoVendita));

        contentPanel.add(immaginiLabel);
        contentPanel.add(aggiungiImmagineButton);
        contentPanel.add(immaginiCounterLabel);
        contentPanel.add(rimuoviImmagineButton);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(backButton);
        contentPanel.add(aggiungiButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }
}