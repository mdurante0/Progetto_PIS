package View;

import Business.PuntoVenditaBusiness;
import Business.Results.PuntoVenditaResult;
import Model.PuntoVendita;
import View.Listener.GoToLoginListener;
import View.Listener.RegistrationListener;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class RegistrationPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confermaPasswordField;
    private JTextField professionField;
    private JTextField ageField;
    private JTextField residenzaField;
    private JTextField telefonoField;
    private JComboBox<String> puntoVenditaBox;

    public RegistrationPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Registrazione");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(0,2));
        JLabel firstNameLabel = new JLabel("  Nome:");
        JLabel lastNameLabel = new JLabel("  Cognome:");
        JLabel emailLabel = new JLabel("  Email:");
        JLabel usernameLabel = new JLabel("  Username:");
        JLabel passwordLabel = new JLabel("  Password:");
        JLabel confermaPasswordLabel = new JLabel("  Conferma password:");
        JLabel professionLabel = new JLabel("  Professione:");
        JLabel ageLabel = new JLabel("  Età:");
        JLabel residenzaLabel = new JLabel("  Residenza:");
        JLabel telefonoLabel = new JLabel("  Telefono:");
        JLabel puntoVenditaLabel = new JLabel("  Punto vendita:");


        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        firstNameLabel.setFont(bodyFont);
        lastNameLabel.setFont(bodyFont);
        emailLabel.setFont(bodyFont);
        usernameLabel.setFont(bodyFont);
        passwordLabel.setFont(bodyFont);
        confermaPasswordLabel.setFont(bodyFont);
        professionLabel.setFont(bodyFont);
        ageLabel.setFont(bodyFont);
        residenzaLabel.setFont(bodyFont);
        telefonoLabel.setFont(bodyFont);
        puntoVenditaLabel.setFont(bodyFont);

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confermaPasswordField = new JPasswordField(20);
        professionField = new JTextField(20);
        ageField = new JTextField(20);
        residenzaField = new JTextField(20);
        telefonoField = new JTextField(20);

        firstNameField.setFont(bodyFont);
        lastNameField.setFont(bodyFont);
        emailField.setFont(bodyFont);
        usernameField.setFont(bodyFont);
        passwordField.setFont(bodyFont);
        confermaPasswordField.setFont(bodyFont);
        professionField.setFont(bodyFont);
        ageField.setFont(bodyFont);
        residenzaField.setFont(bodyFont);
        telefonoField.setFont(bodyFont);

        PuntoVenditaResult result = PuntoVenditaBusiness.getInstance().caricaPuntiVendita();
        if(result.getPuntiVendita() != null) {
            Iterator<PuntoVendita> iterator = result.getPuntiVendita().iterator();
            String[] nomiPV = new String[result.getPuntiVendita().size()];
            for (int i = 0; i < result.getPuntiVendita().size(); i++) {
                nomiPV[i] = iterator.next().getNome();
            }
            puntoVenditaBox = new JComboBox<>(nomiPV);
            puntoVenditaBox.setFocusable(false);
            puntoVenditaBox.setFont(bodyFont);
        }

        JButton registerButton = new JButton("Registrati");
        registerButton.setFont(bodyFont);
        JButton backButton = new JButton("Torna al login");
        backButton.setFont(bodyFont);

        registerButton.addActionListener(new RegistrationListener(this.frame, firstNameField, lastNameField, emailField, usernameField, passwordField, confermaPasswordField, professionField, ageField, residenzaField, telefonoField, puntoVenditaBox));
        backButton.addActionListener(new GoToLoginListener(this.frame));

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
        contentPanel.add(professionLabel);
        contentPanel.add(professionField);
        contentPanel.add(ageLabel);
        contentPanel.add(ageField);
        contentPanel.add(residenzaLabel);
        contentPanel.add(residenzaField);
        contentPanel.add(telefonoLabel);
        contentPanel.add(telefonoField);
        contentPanel.add(puntoVenditaLabel);
        contentPanel.add(puntoVenditaBox);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(backButton);
        contentPanel.add(registerButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }
}
