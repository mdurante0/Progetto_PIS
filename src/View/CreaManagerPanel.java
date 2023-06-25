package View;

import Business.PuntoVenditaBusiness;
import Business.Results.PuntoVenditaResult;
import Model.PuntoVendita;
import View.Listener.AggiungiManagerListener;
import View.Listener.GoToMostraManagerListener;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreaManagerPanel extends JPanel {
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
    private JComboBox<String> puntiVenditaBox;


    public CreaManagerPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Crea Manager");
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
        JLabel puntiVenditaLabel = new JLabel("  Seleziona Punto Vendita:");


        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        firstNameLabel.setFont(bodyFont);
        lastNameLabel.setFont(bodyFont);
        emailLabel.setFont(bodyFont);
        usernameLabel.setFont(bodyFont);
        passwordLabel.setFont(bodyFont);
        confermaPasswordLabel.setFont(bodyFont);
        durataincaricoLabel.setFont(bodyFont);
        puntiVenditaLabel.setFont(bodyFont);


        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confermaPasswordField = new JPasswordField(20);
        durataIncaricoField = new JTextField(20);

        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntiVendita();
        if(!puntoVenditaResult.getPuntiVendita().isEmpty()){
            ArrayList<PuntoVendita> puntiVendita = puntoVenditaResult.getPuntiVendita();
            String[] nomiPuntiVendita = new String[puntoVenditaResult.getPuntiVendita().size()+1];
            for (int i = 0; i < puntiVendita.size(); i++) {
                nomiPuntiVendita[i] = puntiVendita.get(i).getNome();
            }
            puntiVenditaBox = new JComboBox<>(nomiPuntiVendita);
            puntiVenditaBox.setFocusable(false);
            puntiVenditaBox.setFont(bodyFont);
        }


        firstNameField.setFont(bodyFont);
        lastNameField.setFont(bodyFont);
        emailField.setFont(bodyFont);
        usernameField.setFont(bodyFont);
        passwordField.setFont(bodyFont);
        confermaPasswordField.setFont(bodyFont);
        durataIncaricoField.setFont(bodyFont);


        JButton registerButton = new JButton("Aggiungi");
        registerButton.addActionListener(new AggiungiManagerListener(this.frame,firstNameField,lastNameField, emailField, usernameField, passwordField, confermaPasswordField,durataIncaricoField, puntiVenditaBox));

        JButton backButton = new JButton("Indietro");
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
        contentPanel.add(puntiVenditaLabel);
        contentPanel.add(puntiVenditaBox);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(backButton);
        contentPanel.add(registerButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }
}
