package View;


import Model.Articolo;
import Model.Feedback;
import Model.PuntoVendita;
import View.Listener.GoToFeedbackListener;

import javax.swing.*;
import java.awt.*;

public class CreaRispostaFeedbackProdottoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextArea rispostaField;


    public CreaRispostaFeedbackProdottoPanel(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Nuova Risposta");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(4,2));
        JLabel categoriaLabel = new JLabel("  Risposta:");


        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 15);
        categoriaLabel.setFont(bodyFont);


        rispostaField = new JTextArea();



        rispostaField.setFont(bodyFont);


        JButton aggiungiRispostaButton = new JButton("Aggiungi");
        aggiungiRispostaButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToFeedbackListener(this.frame, articolo,puntoVendita));
        tornaIndietroButton.setFont(bodyFont);

        // aggiungere gli action listener

        contentPanel.add(categoriaLabel);
        contentPanel.add(rispostaField);

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(aggiungiRispostaButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
