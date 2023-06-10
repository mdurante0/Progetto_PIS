package View;

import Model.Articolo;
import Model.Servizio;
import Model.composite.IProdotto;
import View.Listener.GoToCatalogoListener;

import javax.swing.*;
import java.awt.*;

public class DettagliPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JComboBox<Integer> quantitaBox;

    public DettagliPanel(MainFrame frame, Articolo articolo) {
        this.frame = frame;
        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Dettagli");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(10,10));

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);

        //id articolo
        JLabel idArticoloLabel = new JLabel("  ID Articolo:");
        idArticoloLabel.setFont(bodyFont);
        contentPanel.add(idArticoloLabel);
        JLabel idArticolo = new JLabel(String.valueOf(articolo.getIdArticolo()));
        idArticolo.setFont(bodyFont);
        contentPanel.add(idArticolo);

        //nome articolo
        JLabel nomeLabel = new JLabel("  Nome:");
        nomeLabel.setFont(bodyFont);
        contentPanel.add(nomeLabel);
        JLabel nome = new JLabel(articolo.getName());
        nome.setFont(bodyFont);
        contentPanel.add(nome);

        //descrizione articolo
        JLabel descrizioneLabel = new JLabel("  Descrizione:");
        descrizioneLabel.setFont(bodyFont);
        contentPanel.add(descrizioneLabel);
        JLabel descrizione = new JLabel(articolo.getDescrizione());
        descrizione.setFont(bodyFont);
        contentPanel.add(descrizione);

        //prezzo articolo
        JLabel prezzoLabel = new JLabel("  Prezzo:");
        prezzoLabel.setFont(bodyFont);
        contentPanel.add(prezzoLabel);
        JLabel prezzo = new JLabel(String.valueOf(articolo.getPrezzo()));
        prezzo.setFont(bodyFont);
        contentPanel.add(prezzo);

        //categoria articolo
        JLabel categoriaLabel;
        JLabel categoria;
        if(articolo.getCategoria() != null) {
             categoriaLabel = new JLabel("  Categoria:");
             categoriaLabel.setFont(bodyFont);
             contentPanel.add(categoriaLabel);
             categoria = new JLabel(articolo.getCategoria().getNome());
             categoria.setFont(bodyFont);
             contentPanel.add(categoria);
        }

        //produttore e quantità o fornitore
        JLabel produttoreLabel;
        JLabel produttore;
        JLabel disponibilitaLabel;
        JLabel disponibilita;
        JLabel fornitoreLabel;
        JLabel fornitore;
        if(articolo instanceof IProdotto prodotto){
            disponibilitaLabel = new JLabel("  Disponibilità:");
            disponibilitaLabel.setFont(bodyFont);
            contentPanel.add(disponibilitaLabel);
            disponibilita = new JLabel(String.valueOf(prodotto.getQuantita()));
            disponibilita.setFont(bodyFont);
            contentPanel.add(disponibilita);
            if(prodotto.getProduttore() != null) {
                produttoreLabel = new JLabel("  Produttore:");
                produttoreLabel.setFont(bodyFont);
                contentPanel.add(produttoreLabel);
                produttore = new JLabel(prodotto.getProduttore().getNome());
                produttore.setFont(bodyFont);
                contentPanel.add(produttore);
            }

            contentPanel.add(new JLabel());
            Integer[] quantita = new Integer[articolo.getQuantita()];
            for(int i = 0; i < articolo.getQuantita(); i++){
                quantita[i] = i+1;
            }
            quantitaBox = new JComboBox<>(quantita);
            contentPanel.add(quantitaBox);


        }else if (articolo instanceof Servizio servizio){
            if(servizio.getFornitore() != null) {
                fornitoreLabel = new JLabel("  Fornitore:");
                fornitoreLabel.setFont(bodyFont);
                contentPanel.add(fornitoreLabel);
                fornitore = new JLabel("  Telefono:");
                fornitore.setFont(bodyFont);
                contentPanel.add(fornitore);
            }
            contentPanel.add(new JLabel());
            contentPanel.add(new JLabel());
        }


        JButton nuovaListaButton = new JButton("Aggiungi ad una nuova lista d'acquisto");
        contentPanel.add(nuovaListaButton);

        JButton listaEsistenteButton = new JButton("Aggiungi ad una lista d'acquisto esistente");
        contentPanel.add(listaEsistenteButton);

        JButton backButton = new JButton("Torna al catalogo");
        backButton.addActionListener(new GoToCatalogoListener(this.frame));
        this.add(backButton, BorderLayout.SOUTH);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }



}
