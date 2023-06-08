package View;

import javax.swing.*;
import java.awt.*;

public class FornitorePanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nome;
    private JTextField emailField;
    private JTextField telefono;
    private JTextField nazione;
    private JTextField citta;
    private JTextField descrizione;
    private JTextField sito;


    public FornitorePanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Fornitore");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(15,2));
        JLabel firstNameLabel = new JLabel("  Nome:");
        JLabel emailLabel = new JLabel("  Email:");
        JLabel telefonoLabel = new JLabel("  Telefono");
        JLabel nazioneLabel = new JLabel("  Nazione:");
        JLabel cittaLabel = new JLabel("  Città:");
        JLabel descrizioneLabel = new JLabel("  Descrizione:");
        JLabel sitoLabel = new JLabel("  Sito:");

        nome = new JTextField(20);
        emailField = new JTextField(20);
        telefono = new JTextField(20);
        nazione = new JTextField(20);
        citta = new JTextField(20);
        descrizione = new JTextField(20);
        sito = new JTextField(40);


        JButton aggiungiProduttore = new JButton("Aggiungi");
        JButton modificaProduttore = new JButton("Modifica");

        // aggiungere gli action listener

        contentPanel.add(firstNameLabel);
        contentPanel.add(nome);
        contentPanel.add(emailLabel);
        contentPanel.add(emailField);
        contentPanel.add(telefonoLabel);
        contentPanel.add(telefono);
        contentPanel.add(nazioneLabel);
        contentPanel.add(nazione);
        contentPanel.add(cittaLabel);
        contentPanel.add(citta);
        contentPanel.add(descrizioneLabel);
        contentPanel.add(descrizione);
        contentPanel.add(sitoLabel);
        contentPanel.add(sito);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(modificaProduttore);
        contentPanel.add(aggiungiProduttore);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }



}
