package View;

import Model.Produttore;
import View.Listener.GoToMostraProduttoriListener;
import View.Listener.ModificaProduttoreListener;

import javax.swing.*;
import java.awt.*;

public class ModificaProduttorePanel extends  JPanel{
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();


    public ModificaProduttorePanel(MainFrame frame, Produttore produttore) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Produttore");
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



        nomeField = new JTextField(produttore.getNome(),20);

        emailField = new JTextField(produttore.getMail(),20);
        telefonoField = new JTextField(produttore.getTelefono(),20);
        nazioneField = new JTextField(produttore.getNazione(),20);
        cittaField = new JTextField(produttore.getCitta(),20);
        descrizioneField = new JTextField(produttore.getDescrizione(),20);
        sitoField = new JTextField(produttore.getSito(),20);


        nomeField.setFont(bodyFont);
        emailField.setFont(bodyFont);
        telefonoField.setFont(bodyFont);
        nazioneField.setFont(bodyFont);
        cittaField.setFont(bodyFont);
        descrizioneField.setFont(bodyFont);
        sitoField.setFont(bodyFont);


        JButton modificaFornitoreButton = new JButton("Modifica");
        modificaFornitoreButton.addActionListener(new ModificaProduttoreListener(this.frame,nomeField,emailField,telefonoField,nazioneField,cittaField,descrizioneField,sitoField, produttore));
        modificaFornitoreButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMostraProduttoriListener(this.frame));
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
