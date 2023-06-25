package View;


import Model.Articolo;
import Model.PuntoVendita;
import View.Listener.CreaListaListener;
import View.Listener.GoToDettagliListener;

import javax.swing.*;
import java.awt.*;

public class CreaListaAcquistoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nomeListaField;


    public CreaListaAcquistoPanel(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Nuova Lista d'acquisto");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(5,2));
        JLabel nomeLabel = new JLabel("  Nome:");

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        nomeLabel.setFont(bodyFont);
        nomeListaField = new JTextField(20);
        nomeListaField.setFont(bodyFont);

        JButton aggiungiListaButton = new JButton("Crea e Aggiungi");
        aggiungiListaButton.addActionListener(new CreaListaListener(this.frame, nomeListaField, articolo, puntoVendita));
        aggiungiListaButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToDettagliListener(this.frame, articolo, puntoVendita));
        tornaIndietroButton.setFont(bodyFont);

        contentPanel.add(nomeLabel);
        contentPanel.add(nomeListaField);

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(aggiungiListaButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
