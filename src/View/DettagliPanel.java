package View;

import Business.ImmagineBusiness;
import Business.Results.ImmagineResult;
import Business.SessionManager;
import Model.*;
import Model.composite.IProdotto;
import View.Listener.GoToCatalogoListener;
import View.Listener.GoToFeedbackListener;
import View.Listener.NextImageListener;
import View.Listener.PreviousImageListener;

import javax.swing.*;
import java.awt.*;

public class DettagliPanel extends JPanel {
    private MainFrame frame;
    private Articolo articolo;
    private JPanel titlePanel = new JPanel();
    private JPanel immaginiPanel = new JPanel();
    private JButton previousImageButton;
    private JButton nextImageButton;
    private ImagePanel imagePanel;
    private int index;
    private JPanel contentPanel = new JPanel();
    private JComboBox<Integer> quantitaBox;
    private JTextField quantitaField;

    public DettagliPanel(MainFrame frame, Articolo articolo, String nomePuntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Dettagli");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        ImmagineResult result = ImmagineBusiness.getInstance().caricaImmaginiArticolo(articolo.getName());
        if(result.getListaImmagini() != null) {
            immaginiPanel.setLayout(new FlowLayout());
            articolo.setImmagini(result.getListaImmagini());

            previousImageButton = new JButton("<-");

            immaginiPanel.add(previousImageButton);

            int index = 0;
            imagePanel = new ImagePanel(articolo.getImmagini().get(index).getPic().getImage());
            immaginiPanel.add(imagePanel);

            nextImageButton = new JButton("->");
            immaginiPanel.add(nextImageButton);

            previousImageButton.addActionListener(new PreviousImageListener(this));
            nextImageButton.addActionListener(new NextImageListener(this));

            this.add(immaginiPanel);
        }

        contentPanel.setLayout(new GridLayout(11,2));
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

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
        JLabel prezzoLabel = new JLabel("  Prezzo (€):");
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
             if(articolo.getCategoria().getNome() != null)
                categoria = new JLabel(articolo.getCategoria().getNome());
             else
                 categoria = new JLabel("Categoria non assegnata");
             categoria.setFont(bodyFont);
             contentPanel.add(categoria);
        } else {
            contentPanel.add(new JLabel());
            contentPanel.add(new JLabel());
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
            } else {
                contentPanel.add(new JLabel());
                contentPanel.add(new JLabel());
            }
            if(u instanceof Cliente) {
                JLabel selezionaQuantitaLabel = new JLabel("  Seleziona la quantità da acquistare:");
                selezionaQuantitaLabel.setFont(bodyFont);
                contentPanel.add(selezionaQuantitaLabel);
                Integer[] quantita = new Integer[articolo.getQuantita()];
                for (int i = 0; i < articolo.getQuantita(); i++) {
                    quantita[i] = i + 1;
                }
                quantitaBox = new JComboBox<>(quantita);
                quantitaBox.setFont(bodyFont);
                quantitaBox.setFocusable(false);
                contentPanel.add(quantitaBox);
            }
            else if(u instanceof Manager || u instanceof Amministratore) {
                JLabel selezionaQuantitaLabel = new JLabel("  Inserisci la disponibilità:");
                selezionaQuantitaLabel.setFont(bodyFont);
                contentPanel.add(selezionaQuantitaLabel);
                quantitaField = new JTextField(20);
                quantitaField.setFont(bodyFont);
                contentPanel.add(quantitaField);
            }


        }else if (articolo instanceof Servizio servizio){
            if(servizio.getFornitore() != null) {
                fornitoreLabel = new JLabel("  Fornitore:");
                fornitoreLabel.setFont(bodyFont);
                contentPanel.add(fornitoreLabel);
                fornitore = new JLabel(servizio.getFornitore().getNome());
                fornitore.setFont(bodyFont);
                contentPanel.add(fornitore);
            } else {
                contentPanel.add(new JLabel());
                contentPanel.add(new JLabel());
            }

        }

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());


        if(u instanceof Cliente) {
            JButton nuovaListaButton = new JButton("Aggiungi ad una nuova lista d'acquisto");
            nuovaListaButton.setFont(bodyFont);
            contentPanel.add(nuovaListaButton);

            JButton listaEsistenteButton = new JButton("Aggiungi ad una lista d'acquisto esistente");
            listaEsistenteButton.setFont(bodyFont);
            contentPanel.add(listaEsistenteButton);
        }
        else if(u instanceof Manager && articolo instanceof IProdotto){
            JButton modificaDisponibilita = new JButton("Modifica disponibilita");
            modificaDisponibilita.setFont(bodyFont);
            contentPanel.add(new JLabel());
            contentPanel.add(modificaDisponibilita);
        } else if (u instanceof Amministratore) {
            JButton rimuoviArticolo = new JButton("Rimuovi questo articolo dal catalogo");
            JButton modificaArticolo = new JButton("Modifica articolo");
            rimuoviArticolo.setFont(bodyFont);
            modificaArticolo.setFont(bodyFont);
            contentPanel.add(modificaArticolo);
            contentPanel.add(rimuoviArticolo);
        }
        else {
            contentPanel.add(new JLabel());
            contentPanel.add(new JLabel());
        }

        JButton backButton = new JButton("Torna al catalogo");
        backButton.addActionListener(new GoToCatalogoListener(this.frame, nomePuntoVendita));
        backButton.setFont(bodyFont);

        JButton feedbackButton = new JButton("Mostra i feedback");
        feedbackButton.addActionListener(new GoToFeedbackListener(this.frame, articolo, nomePuntoVendita));
        feedbackButton.setFont(bodyFont);

        contentPanel.add(feedbackButton);
        contentPanel.add(backButton);


        this.add(titlePanel);

        this.add(contentPanel);
    }

    public void nextImage(){
        if(articolo.getImmagini() != null) {
            if (index < articolo.getImmagini().size() - 1) {
                immaginiPanel.remove(imagePanel);
                index++;
                imagePanel = new ImagePanel(articolo.getImmagini().get(index).getPic().getImage());
                immaginiPanel.add(imagePanel);
                immaginiPanel.remove(nextImageButton);
                immaginiPanel.add(nextImageButton);
                repaint();
                revalidate();
            }
        }
    }
    public void previousImage() {
        if(articolo.getImmagini() != null) {
            if (index > 0) {
                immaginiPanel.remove(imagePanel);
                index--;
                imagePanel = new ImagePanel(articolo.getImmagini().get(index).getPic().getImage());
                immaginiPanel.add(imagePanel);
                immaginiPanel.remove(nextImageButton);
                immaginiPanel.add(nextImageButton);
                repaint();
                revalidate();
            }
        }
    }
}
