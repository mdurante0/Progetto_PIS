package View;

import Business.ArticoloBusiness;
import Business.ImmagineBusiness;
import Business.Results.ArticoloResult;
import Business.Results.ImmagineResult;
import Business.SessionManager;
import Model.*;
import Model.composite.IProdotto;
import Model.composite.ProdottoComposito;
import View.Listener.*;

import javax.swing.*;
import java.awt.*;

public class DettagliComponentePanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel immaginiPanel = new JPanel();
    private JButton previousImageButton;
    private JButton nextImageButton;
    private ImagePanel imagePanel;
    private JPanel contentPanel = new JPanel();

    public DettagliComponentePanel(MainFrame frame, Articolo articolo, ProdottoComposito prodottoComposito, String nomePuntoVendita) {
        this.frame = frame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Dettagli componente");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        ImmagineResult result = ImmagineBusiness.getInstance().caricaImmaginiByArticolo(articolo.getName());
        if(!result.getListaImmagini().isEmpty()) {
            immaginiPanel.setLayout(new FlowLayout());
            articolo.setImmagini(result.getListaImmagini());

            previousImageButton = new JButton("<-");

            immaginiPanel.add(previousImageButton);

            int index = 0;
            imagePanel = new ImagePanel(articolo.getImmagini().get(index).getPic().getImage());
            immaginiPanel.add(imagePanel);

            nextImageButton = new JButton("->");
            immaginiPanel.add(nextImageButton);

            previousImageButton.addActionListener(new ImageListener(this, imagePanel, articolo));
            nextImageButton.addActionListener(new ImageListener(this, imagePanel, articolo));

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
        categoriaLabel = new JLabel("  Categoria:");
        categoriaLabel.setFont(bodyFont);
        contentPanel.add(categoriaLabel);
        if(articolo.getCategoria().getNome() != null)
            categoria = new JLabel(articolo.getCategoria().getNome());
        else
            categoria = new JLabel("Categoria non assegnata");
        categoria.setFont(bodyFont);
        contentPanel.add(categoria);


        //produttore e quantità
        JLabel produttoreLabel;
        JLabel produttore;
        JLabel disponibilitaLabel;
        JLabel disponibilita;

        if(articolo instanceof IProdotto prodotto){
            disponibilitaLabel = new JLabel("  Quantità:");
            disponibilitaLabel.setFont(bodyFont);
            contentPanel.add(disponibilitaLabel);
            disponibilita = new JLabel(String.valueOf(prodotto.getQuantita()));
            disponibilita.setFont(bodyFont);
            contentPanel.add(disponibilita);
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
        }

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());

        ArticoloResult articoloResult = ArticoloBusiness.getInstance().getType(articolo);
        if(articoloResult.getArticolo() instanceof ProdottoComposito pc){
            JButton mostraComponentiButton = new JButton("Mostra i componenti");
            mostraComponentiButton.setFont(bodyFont);
            mostraComponentiButton.addActionListener(new GoToMostraComponentiListener(this.frame, pc, nomePuntoVendita));
            contentPanel.add(mostraComponentiButton);
        }

        JButton backButton = new JButton("Torna al prodotto composito");
        backButton.addActionListener(new GoToMostraComponentiListener(this.frame, prodottoComposito, nomePuntoVendita));
        backButton.setFont(bodyFont);

        JButton feedbackButton = new JButton("Mostra i feedback");
        feedbackButton.addActionListener(new GoToFeedbackListener(this.frame, articolo, prodottoComposito, nomePuntoVendita));
        feedbackButton.setFont(bodyFont);

        contentPanel.add(feedbackButton);
        contentPanel.add(backButton);

        this.add(titlePanel);
        this.add(contentPanel);
    }
}
