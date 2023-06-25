package View;

import Model.Manager;
import View.Listener.GoToMostraManagerListener;
import View.Listener.ModificaManagerListener;

import javax.swing.*;
import java.awt.*;

public class ModificaManagerPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confermaPasswordField;
    private JTextField durataIncaricoField;


    public ModificaManagerPanel(MainFrame frame, Manager manager) {
        this.frame = frame;

        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Modifica Manager");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(15,2));
        JLabel firstNameLabel = new JLabel("  Nome:");
        JLabel lastNameLabel = new JLabel("  Cognome:");
        JLabel emailLabel = new JLabel("  Email:");
        JLabel usernameLabel = new JLabel("  Username:");
        JLabel passwordLabel = new JLabel("  Password:");
        JLabel confermaPasswordLabel = new JLabel("  Conferma password:");
        JLabel durataincaricoLabel = new JLabel("  Durata incarico:");


        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        firstNameLabel.setFont(bodyFont);
        lastNameLabel.setFont(bodyFont);
        emailLabel.setFont(bodyFont);
        usernameLabel.setFont(bodyFont);
        passwordLabel.setFont(bodyFont);
        confermaPasswordLabel.setFont(bodyFont);
        durataincaricoLabel.setFont(bodyFont);


        firstNameField = new JTextField(manager.getName(),20);
        lastNameField = new JTextField(manager.getSurname(),20);
        emailField = new JTextField(manager.getEmail(),20);
        usernameField = new JTextField(manager.getUsername(),20);
        passwordField = new JPasswordField(manager.getPwd(), 20);
        confermaPasswordField = new JPasswordField(manager.getPwd(),20);
        durataIncaricoField = new JTextField( String.valueOf(manager.getDurataIncarico()), 20);

        firstNameField.setFont(bodyFont);
        lastNameField.setFont(bodyFont);
        emailField.setFont(bodyFont);
        usernameField.setFont(bodyFont);
        passwordField.setFont(bodyFont);
        confermaPasswordField.setFont(bodyFont);
        durataIncaricoField.setFont(bodyFont);


        JButton registerButton = new JButton("Modifica");
        JButton backButton = new JButton("Indietro");

        // ggiungere action listener
        registerButton.addActionListener(new ModificaManagerListener(this.frame, firstNameField, lastNameField, emailField, usernameField, passwordField, confermaPasswordField, durataIncaricoField, manager));
        backButton.addActionListener(new GoToMostraManagerListener(this.frame));

        contentPanel.add(firstNameLabel);
        contentPanel.add(firstNameField);
        contentPanel.add(lastNameLabel);
        contentPanel.add(lastNameField);
        contentPanel.add(emailLabel);
        contentPanel.add(emailField);
        contentPanel.add(usernameLabel);
        contentPanel.add(usernameField);
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);
        contentPanel.add(confermaPasswordLabel);
        contentPanel.add(confermaPasswordField);
        contentPanel.add(durataincaricoLabel);
        contentPanel.add(durataIncaricoField);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(backButton);
        contentPanel.add(registerButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }
}
