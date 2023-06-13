package View;

import javax.swing.*;
import java.awt.*;

public class ProduttorePanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField telefonoField;
    private JTextField nazioneField;
    private JTextField cittaField;
    private JTextField descrizioneField;
    private JTextField sitoField;


    public ProduttorePanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Produttore");
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

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        firstNameLabel.setFont(bodyFont);
        emailLabel.setFont(bodyFont);
        telefonoLabel.setFont(bodyFont);
        nazioneLabel.setFont(bodyFont);
        cittaLabel.setFont(bodyFont);
        descrizioneLabel.setFont(bodyFont);
        sitoLabel.setFont(bodyFont);

        nomeField = new JTextField(20);
        emailField = new JTextField(20);
        telefonoField = new JTextField(20);
        nazioneField = new JTextField(20);
        cittaField = new JTextField(20);
        descrizioneField = new JTextField(20);
        sitoField = new JTextField(20);

        nomeField.setFont(bodyFont);
        emailField.setFont(bodyFont);
        telefonoField.setFont(bodyFont);
        nazioneField.setFont(bodyFont);
        cittaField.setFont(bodyFont);
        descrizioneField.setFont(bodyFont);
        sitoField.setFont(bodyFont);


        JButton aggiungiProduttoreButton = new JButton("Aggiungi");
        aggiungiProduttoreButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.setFont(bodyFont);

        // aggiungere gli action listener

        contentPanel.add(firstNameLabel);
        contentPanel.add(nomeField);
        contentPanel.add(emailLabel);
        contentPanel.add(emailField);
        contentPanel.add(telefonoLabel);
        contentPanel.add(telefonoField);
        contentPanel.add(nazioneLabel);
        contentPanel.add(nazioneField);
        contentPanel.add(cittaLabel);
        contentPanel.add(cittaField);
        contentPanel.add(descrizioneLabel);
        contentPanel.add(descrizioneField);
        contentPanel.add(sitoLabel);
        contentPanel.add(sitoField);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(aggiungiProduttoreButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }
}
