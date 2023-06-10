package View;

import Business.ImmagineBusiness;
import Model.Articolo;
import Model.Servizio;
import Model.composite.IProdotto;
import View.Listener.GoToCatalogoListener;
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

    public DettagliPanel(MainFrame frame, Articolo articolo) {
        this.frame = frame;
        this.articolo = articolo;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Dettagli");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        immaginiPanel.setLayout(new FlowLayout());
        articolo.setImmagini(ImmagineBusiness.getInstance().caricaImmaginiArticolo(articolo.getName()).getListaImmagini());

        previousImageButton = new JButton("<-");

        immaginiPanel.add(previousImageButton);

        int index = 0;
        imagePanel = new ImagePanel(articolo.getImmagini().get(index).getPic().getImage());
        immaginiPanel.add(imagePanel);

        nextImageButton = new JButton("->");
        immaginiPanel.add(nextImageButton);

        previousImageButton.addActionListener(new PreviousImageListener(this));
        nextImageButton.addActionListener(new NextImageListener(this));

        contentPanel.setLayout(new GridLayout(11,2));
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());

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
             categoria = new JLabel(articolo.getCategoria().getNome());
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

            JLabel selezionaQuantitaLabel = new JLabel("  Seleziona la quantità da acquistare:");
            selezionaQuantitaLabel.setFont(bodyFont);
            contentPanel.add(selezionaQuantitaLabel);
            Integer[] quantita = new Integer[articolo.getQuantita()];
            for(int i = 0; i < articolo.getQuantita(); i++){
                quantita[i] = i+1;
            }
            quantitaBox = new JComboBox<>(quantita);
            quantitaBox.setFont(bodyFont);
            quantitaBox.setFocusable(false);
            contentPanel.add(quantitaBox);


        }else if (articolo instanceof Servizio servizio){
            if(servizio.getFornitore() != null) {
                fornitoreLabel = new JLabel("  Fornitore:");
                fornitoreLabel.setFont(bodyFont);
                contentPanel.add(fornitoreLabel);
                fornitore = new JLabel("  Telefono:");
                fornitore.setFont(bodyFont);
                contentPanel.add(fornitore);
            } else {
                contentPanel.add(new JLabel());
                contentPanel.add(new JLabel());
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

        JButton feedbackButton = new JButton("Mostra i feedback");

        //southPanel.setLayout(new GridLayout(1,2));
        contentPanel.add(feedbackButton);
        contentPanel.add(backButton);


        this.add(titlePanel);
        this.add(immaginiPanel);
        this.add(contentPanel);
        //this.add(southPanel);

        setVisible(true);
    }

    public void nextImage(){
        if(index < articolo.getImmagini().size() - 1){
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
    public void previousImage() {
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
