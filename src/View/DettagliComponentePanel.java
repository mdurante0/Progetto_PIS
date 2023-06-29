package View;

import Business.ArticoloBusiness;
import Business.ImmagineBusiness;
import Business.Results.ArticoloResult;
import Business.Results.ImmagineResult;
import Model.Articolo;
import Model.PuntoVendita;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import Model.composite.ProdottoComposito;
import View.Listener.GoToFeedbackListener;
import View.Listener.GoToMostraComponentiListener;
import View.Listener.ImageListener;

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

    public DettagliComponentePanel(MainFrame frame, IProdotto prodotto, ProdottoComposito prodottoComposito, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Dettagli componente");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        this.add(titlePanel);

        ImmagineResult result = ImmagineBusiness.getInstance().caricaImmaginiByArticolo(prodotto.getName());
        if(!result.getListaImmagini().isEmpty()) {
            immaginiPanel.setLayout(new FlowLayout());
            immaginiPanel.setPreferredSize(new Dimension(260,260));
            prodotto.setImmagini(result.getListaImmagini());

            previousImageButton = new JButton("<-");
            previousImageButton.setActionCommand(ImageListener.PREVIOUS);
            immaginiPanel.add(previousImageButton);

            imagePanel = new ImagePanel(prodotto.getImmagini().get(0).getPic().getImage());
            immaginiPanel.add(imagePanel);

            nextImageButton = new JButton("->");
            nextImageButton.setActionCommand(ImageListener.NEXT);
            immaginiPanel.add(nextImageButton);

            ImageListener imageListener = new ImageListener(this, imagePanel,(Articolo) prodotto);
            previousImageButton.addActionListener(imageListener);
            nextImageButton.addActionListener(imageListener);

            this.add(immaginiPanel);
        }

        contentPanel.setLayout(new GridLayout(0,2));
        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);

        //nome articolo
        JLabel nomeLabel = new JLabel("  Nome:");
        nomeLabel.setFont(bodyFont);
        contentPanel.add(nomeLabel);
        JLabel nome = new JLabel(prodotto.getName());
        nome.setFont(bodyFont);
        contentPanel.add(nome);

        //descrizione articolo
        JLabel descrizioneLabel = new JLabel("  Descrizione:");
        descrizioneLabel.setFont(bodyFont);
        contentPanel.add(descrizioneLabel);
        JLabel descrizione = new JLabel(prodotto.getDescrizione());
        descrizione.setFont(bodyFont);
        contentPanel.add(descrizione);

        //prezzo articolo
        JLabel prezzoLabel = new JLabel("  Prezzo (€):");
        prezzoLabel.setFont(bodyFont);
        contentPanel.add(prezzoLabel);
        JLabel prezzo = new JLabel(String.valueOf(prodotto.getPrezzo()));
        prezzo.setFont(bodyFont);
        contentPanel.add(prezzo);

        //categoria articolo
        JLabel categoriaLabel;
        JLabel categoria;
        categoriaLabel = new JLabel("  Categoria:");
        categoriaLabel.setFont(bodyFont);
        contentPanel.add(categoriaLabel);
        if(prodotto.getCategoria().getNome() != null)
            categoria = new JLabel(prodotto.getCategoria().getNome());
        else
            categoria = new JLabel("Categoria non assegnata");
        categoria.setFont(bodyFont);
        contentPanel.add(categoria);

        //produttore e quantità
        JLabel produttoreLabel;
        JLabel produttore;
        JLabel disponibilitaLabel;
        JLabel disponibilita;

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

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());

        ArticoloResult articoloResult = ArticoloBusiness.getInstance().getType((Prodotto) prodotto);
        if(articoloResult.getArticolo() instanceof ProdottoComposito pc){
            JButton mostraComponentiButton = new JButton("Mostra i componenti");
            mostraComponentiButton.setFont(bodyFont);
            mostraComponentiButton.addActionListener(new GoToMostraComponentiListener(this.frame, pc, puntoVendita));
            contentPanel.add(mostraComponentiButton);
        }

        JButton backButton = new JButton("Torna al prodotto composito");
        backButton.addActionListener(new GoToMostraComponentiListener(this.frame, prodottoComposito, puntoVendita));
        backButton.setFont(bodyFont);

        JButton feedbackButton = new JButton("Mostra i feedback");
        feedbackButton.addActionListener(new GoToFeedbackListener(this.frame,(Articolo) prodotto, prodottoComposito, puntoVendita));
        feedbackButton.setFont(bodyFont);

        contentPanel.add(feedbackButton);
        contentPanel.add(backButton);


        this.add(contentPanel);
    }
}
