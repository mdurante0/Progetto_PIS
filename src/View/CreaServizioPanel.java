package View;

import Business.CategoriaBusiness;
import Business.FornitoreBusiness;
import Business.Results.CategoriaResult;
import Business.Results.FornitoreResult;
import Model.CategoriaServizio;
import Model.Fornitore;
import View.Listener.CreaServizioListener;
import View.Listener.GoToMenuListener;
import View.Listener.ImmaginiListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class CreaServizioPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nomeServizioField;
    private JTextField descrizioneField;
    private JTextField prezzoField;
    private JComboBox<String> fornitoreBox;
    private JComboBox<String> categoriaServizioBox;

    private ArrayList<File> files = new ArrayList<>();
    private JLabel immaginiCounterLabel;

    public CreaServizioPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Nuovo servizio");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(12, 2));
        JLabel nomeProdottoLabel = new JLabel("  Nome:");
        JLabel descrizioneLabel = new JLabel("  Descrizione:");
        JLabel prezzoLabel = new JLabel("  Prezzo (€):");

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        nomeProdottoLabel.setFont(bodyFont);
        descrizioneLabel.setFont(bodyFont);
        prezzoLabel.setFont(bodyFont);

        nomeServizioField = new JTextField(20);
        descrizioneField = new JTextField(20);
        prezzoField = new JTextField(20);

        nomeServizioField.setFont(bodyFont);
        descrizioneField.setFont(bodyFont);
        prezzoField.setFont(bodyFont);

        contentPanel.add(nomeProdottoLabel);
        contentPanel.add(nomeServizioField);
        contentPanel.add(descrizioneLabel);
        contentPanel.add(descrizioneField);
        contentPanel.add(prezzoLabel);
        contentPanel.add(prezzoField);

        FornitoreResult fornitoreResult = FornitoreBusiness.getInstance().caricaFornitori();
        if (fornitoreResult.getFornitori() != null){
            Iterator<Fornitore> iterator = fornitoreResult.getFornitori().iterator();
            String[] nomiFornitori = new String[fornitoreResult.getFornitori().size()];
            for (int i = 0; i < fornitoreResult.getFornitori().size(); i++) {
                nomiFornitori[i] = iterator.next().getNome();
            }
            fornitoreBox = new JComboBox<>(nomiFornitori);
            fornitoreBox.setFocusable(false);
            fornitoreBox.setFont(bodyFont);

            JLabel produttoreLabel = new JLabel("  Fornitore del nuovo servizio:");
            produttoreLabel.setFont(bodyFont);
            fornitoreBox.setFont(bodyFont);
            contentPanel.add(produttoreLabel);
            contentPanel.add(fornitoreBox);
        }

        CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategorieServizio();
        if(categoriaResult.getCategorieProdotto() != null) {
            Iterator<CategoriaServizio> iterator = categoriaResult.getCategorieServizio().iterator();
            String[] nomiCategorieServizio = new String[categoriaResult.getCategorieServizio().size() + 1];
            for (int i = 0; i < categoriaResult.getCategorieServizio().size(); i++) {
                nomiCategorieServizio[i] = iterator.next().getNome();
            }
            nomiCategorieServizio[nomiCategorieServizio.length - 1] = "Nessuna Categoria";
            categoriaServizioBox = new JComboBox<>(nomiCategorieServizio);
            categoriaServizioBox.setFocusable(false);
            categoriaServizioBox.setFont(bodyFont);

            JLabel categoriaLabel = new JLabel("  Categoria da assegnare al nuovo servizio:");
            categoriaLabel.setFont(bodyFont);
            categoriaServizioBox.setFont(bodyFont);
            contentPanel.add(categoriaLabel);
            contentPanel.add(categoriaServizioBox);
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

        JButton aggiungiButton = new JButton("Aggiunti servizio");
        aggiungiButton.setFont(bodyFont);
        aggiungiButton.addActionListener(new CreaServizioListener(this.frame, nomeServizioField, descrizioneField, prezzoField, fornitoreBox, categoriaServizioBox, files));


        JButton backButton = new JButton("Torna indietro");
        backButton.setFont(bodyFont);
        backButton.addActionListener(new GoToMenuListener(this.frame));

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
