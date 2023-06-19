package View;

import Model.Fornitore;
import View.Listener.GoToMostraFornitoriListener;
import View.Listener.ModificaFornitoreListener;
import View.Listener.ModificaProduttoreListener;

import javax.swing.*;
import java.awt.*;

public class ModificaFornitorePanel extends  JPanel{
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();


    public ModificaFornitorePanel(MainFrame frame, Fornitore fornitore) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Fornitore");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        JTextField nomeField;
        JTextField emailField;
        JTextField telefonoField;
        JTextField nazioneField;
        JTextField cittaField;
        JTextField descrizioneField;
        JTextField sitoField;


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



        nomeField = new JTextField(fornitore.getNome(),20);

        emailField = new JTextField(fornitore.getMail(),20);
        telefonoField = new JTextField(fornitore.getTelefono(),20);
        nazioneField = new JTextField(fornitore.getNazione(),20);
        cittaField = new JTextField(fornitore.getCitta(),20);
        descrizioneField = new JTextField(fornitore.getDescrizione(),20);
        sitoField = new JTextField(fornitore.getSito(),20);


        nomeField.setFont(bodyFont);
        emailField.setFont(bodyFont);
        telefonoField.setFont(bodyFont);
        nazioneField.setFont(bodyFont);
        cittaField.setFont(bodyFont);
        descrizioneField.setFont(bodyFont);
        sitoField.setFont(bodyFont);


        JButton modificaFornitoreButton = new JButton("Modifica");
        modificaFornitoreButton.addActionListener(new ModificaFornitoreListener(this.frame, nomeField,emailField,telefonoField,nazioneField,cittaField,descrizioneField,sitoField));
        modificaFornitoreButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMostraFornitoriListener(this.frame));
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
        contentPanel.add(modificaFornitoreButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }

}
