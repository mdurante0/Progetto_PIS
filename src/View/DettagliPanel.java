package View;

import Business.ArticoloBusiness;
import Business.CollocazioneBusiness;
import Business.ImmagineBusiness;
import Business.Results.ArticoloResult;
import Business.Results.CollocazioneResult;
import Business.Results.ImmagineResult;
import Business.SessionManager;
import Model.*;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import Model.composite.ProdottoComposito;
import View.Listener.*;

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
    private JPanel contentPanel = new JPanel();
    private JComboBox<Integer> quantitaBox;
    private JTextField quantitaField;
    private JTextField quantitaPrenotazioneField;

    public DettagliPanel(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Dettagli");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        this.add(titlePanel);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);

        ImmagineResult result = ImmagineBusiness.getInstance().caricaImmaginiByArticolo(articolo.getName());
        if(!result.getListaImmagini().isEmpty()) {
            immaginiPanel.setLayout(new FlowLayout());
            articolo.setImmagini(result.getListaImmagini());
            immaginiPanel.setPreferredSize(new Dimension(250,250));
            previousImageButton = new JButton("<-");
            previousImageButton.setActionCommand(ImageListener.PREVIOUS);

            immaginiPanel.add(previousImageButton);

            imagePanel = new ImagePanel(articolo.getImmagini().get(0).getPic().getImage());
            immaginiPanel.add(imagePanel);

            nextImageButton = new JButton("->");
            nextImageButton.setActionCommand(ImageListener.NEXT);
            immaginiPanel.add(nextImageButton);

            ImageListener imageListener = new ImageListener(this, imagePanel, articolo);
            previousImageButton.addActionListener(imageListener);
            nextImageButton.addActionListener(imageListener);

            this.add(immaginiPanel);
        }

        contentPanel.setLayout(new GridLayout(0,2));
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
        categoriaLabel = new JLabel("  Categoria:");
        categoriaLabel.setFont(bodyFont);
        contentPanel.add(categoriaLabel);
        if(articolo.getCategoria().getNome() != null)
            categoria = new JLabel(articolo.getCategoria().getNome());
        else
            categoria = new JLabel("Categoria non assegnata");
        categoria.setFont(bodyFont);
        contentPanel.add(categoria);

        //produttore e quantità o fornitore
        JLabel produttoreLabel;
        JLabel produttore;
        JLabel disponibilitaLabel;
        JLabel disponibilita;
        JLabel corsiaLabel;
        JLabel corsia;
        JLabel scaffaleLabel;
        JLabel scaffale;
        JLabel fornitoreLabel;
        JLabel fornitore;
        
        Collocazione collocazione = new Collocazione();
        if(articolo instanceof IProdotto prodotto){
            if(puntoVendita != null) {
                disponibilitaLabel = new JLabel("  Disponibilità:");
                disponibilitaLabel.setFont(bodyFont);
                contentPanel.add(disponibilitaLabel);
                disponibilita = new JLabel(String.valueOf(prodotto.getQuantita()));
                disponibilita.setFont(bodyFont);
                contentPanel.add(disponibilita);
            }
            if(prodotto.getProduttore().getNome() != null) {
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
            if (puntoVendita != null) {
                CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().caricaCollocazioneById(prodotto.getCollocazione().getIdCollocazione());
                collocazione = collocazioneResult.getCollocazioni().get(0);

                prodotto.setCollocazione(collocazione);
                corsiaLabel = new JLabel("  Corsia:");
                corsiaLabel.setFont(bodyFont);
                contentPanel.add(corsiaLabel);
                corsia = new JLabel(String.valueOf(prodotto.getCollocazione().getCorsia()));
                corsia.setFont(bodyFont);
                contentPanel.add(corsia);

                scaffaleLabel = new JLabel("  Scaffale:");
                scaffaleLabel.setFont(bodyFont);
                contentPanel.add(scaffaleLabel);
                scaffale = new JLabel(String.valueOf(prodotto.getCollocazione().getScaffale()));
                scaffale.setFont(bodyFont);
                contentPanel.add(scaffale);
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

                JLabel prenotaLabel = new JLabel("  Seleziona la quantità da prenotare:");
                prenotaLabel.setFont(bodyFont);
                contentPanel.add(prenotaLabel);
                quantitaPrenotazioneField = new JTextField(20);
                quantitaPrenotazioneField.setFont(bodyFont);
                contentPanel.add(quantitaPrenotazioneField);

                JButton prenotaButton = new JButton("Prenota");
                prenotaButton.addActionListener(new PrenotazioneListener(this.frame, prodotto, quantitaPrenotazioneField));
                prenotaButton.setFont(bodyFont);
                contentPanel.add(new JLabel());
                contentPanel.add(prenotaButton);
            }
            else if(u instanceof Manager) {
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

        ArticoloResult articoloResult = ArticoloBusiness.getInstance().getType(articolo);
        if(articoloResult.getArticolo() instanceof ProdottoComposito prodottoComposito){
            if(puntoVendita != null)
                prodottoComposito.setCollocazione(collocazione);
            prodottoComposito.setQuantita(articolo.getQuantita());
            JButton mostraComponentiButton = new JButton("Mostra i componenti");
            mostraComponentiButton.setFont(bodyFont);
            mostraComponentiButton.addActionListener(new GoToMostraComponentiListener(this.frame, prodottoComposito, puntoVendita));
            contentPanel.add(mostraComponentiButton);

        } else contentPanel.add(new JLabel());

        if(u instanceof Cliente) {
            JButton nuovaListaButton = new JButton("Aggiungi ad una nuova lista d'acquisto");
            nuovaListaButton.addActionListener(new GoToCreaListaListener(this.frame, quantitaBox, articolo, puntoVendita));
            nuovaListaButton.setFont(bodyFont);
            contentPanel.add(nuovaListaButton);

            JButton listaEsistenteButton = new JButton("Aggiungi ad una lista d'acquisto esistente");
            listaEsistenteButton.addActionListener(new GoToAggiungiAListaListener(this.frame, quantitaBox, articolo, puntoVendita));
            listaEsistenteButton.setFont(bodyFont);
            contentPanel.add(listaEsistenteButton);
        }
        else if(u instanceof Manager m && articolo instanceof IProdotto prodotto){
            JButton modificaDisponibilita = new JButton("Modifica disponibilita");
            modificaDisponibilita.addActionListener(new RifornimentoListener(this.frame, quantitaField, prodotto, m));
            articolo.setQuantita(prodotto.getQuantita());
            modificaDisponibilita.setFont(bodyFont);
            contentPanel.add(new JLabel());
            contentPanel.add(modificaDisponibilita);
        } else if (u instanceof Amministratore) {
            JButton rimuoviArticolo = new JButton("Rimuovi questo articolo");
            rimuoviArticolo.addActionListener(new RemoveArticoloListener(this.frame, articolo, puntoVendita));
            JButton modificaArticolo = new JButton("Modifica articolo");

            if(articoloResult.getArticolo() instanceof ProdottoComposito prodottoComposito) {
                if(puntoVendita != null)
                    prodottoComposito.setCollocazione(collocazione);
                prodottoComposito.setQuantita(articolo.getQuantita());
                prodottoComposito.setImmagini(articolo.getImmagini());
                modificaArticolo.addActionListener(new GoToModificaProdottoCompositoListener(this.frame, prodottoComposito, puntoVendita));
            }
            else if(articolo instanceof Prodotto prodotto)
                modificaArticolo.addActionListener(new GoToModificaProdottoListener(this.frame, prodotto, puntoVendita));
            else if(articolo instanceof Servizio servizio)
                modificaArticolo.addActionListener(new GoToModificaServizioListener(this.frame, servizio));

            rimuoviArticolo.setFont(bodyFont);
            modificaArticolo.setFont(bodyFont);
            contentPanel.add(modificaArticolo);
            contentPanel.add(rimuoviArticolo);
        }

        JButton backButton = new JButton("Torna al catalogo");
        if(articolo instanceof IProdotto)
            backButton.setActionCommand(GoToCatalogoListener.PRODOTTI);
        else if (articolo instanceof Servizio)
            backButton.setActionCommand(GoToCatalogoListener.SERVIZI);
        backButton.addActionListener(new GoToCatalogoListener(this.frame, puntoVendita));
        backButton.setFont(bodyFont);

        JButton feedbackButton = new JButton("Mostra i feedback");
        feedbackButton.addActionListener(new GoToFeedbackListener(this.frame, articolo, puntoVendita));
        feedbackButton.setFont(bodyFont);

        contentPanel.add(feedbackButton);
        contentPanel.add(backButton);


        this.add(scrollPane);
    }
}
