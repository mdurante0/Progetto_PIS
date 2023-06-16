package View;


import Business.CatalogoBusiness;
import Business.Results.CatalogoResult;
import Model.PuntoVendita;
import Model.composite.IProdotto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreaPrenotazionePanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JComboBox<String> prodottiField;
    private JComboBox<Integer> quantitaField;


    public CreaPrenotazionePanel(MainFrame frame, PuntoVendita puntoVendita) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Nuova Prenotazione");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(6,2));
        JLabel prodottiLabel = new JLabel("  Seleziona Prodotto:");
        JLabel quantitaLabel = new JLabel("  Commento:");
        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);

        prodottiLabel.setFont(bodyFont);
        quantitaLabel.setFont(bodyFont);


        CatalogoResult result = CatalogoBusiness.getInstance().caricaCatalogo(puntoVendita.getNome());
        ArrayList<IProdotto> prodotti = result.getListaProdotti();
        String [] nomiProdotti = new String[prodotti.size()];
        for(int i=0; i<prodotti.size(); i++){
            nomiProdotti[i] = prodotti.get(i).getName();
        }

        prodottiField = new JComboBox<>(nomiProdotti);
        prodottiField.setFont(bodyFont);
        quantitaField.setFont(bodyFont);

        int massimaQuantita = 99;
        Integer[] quantita = new Integer[massimaQuantita];
        for (int i = 0; i < massimaQuantita; i++) {
            quantita[i] = i + 1;
        }

        quantitaField = new JComboBox<>(quantita);
        quantitaField.setFont(bodyFont);
        quantitaField.setFont(bodyFont);




        JButton creaFeedback = new JButton("Aggiungi alla lista");
        creaFeedback.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.setFont(bodyFont);

        // aggiungere gli action listener

        contentPanel.add(prodottiLabel);
        contentPanel.add(prodottiField);

        contentPanel.add(quantitaLabel);
        contentPanel.add(quantitaField);

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(creaFeedback);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
