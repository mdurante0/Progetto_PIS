package View;

import View.Listener.BackToLoginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField professionField;
    private JTextField ageField;
    private JTextField residenzaField;
    private JTextField telefonoField;
    private JTextField puntoVenditaField;

    public RegistrationPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Registrazione");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(21,2));
        JLabel firstNameLabel = new JLabel("  Nome:");
        JLabel lastNameLabel = new JLabel("  Cognome:");
        JLabel emailLabel = new JLabel("  Email:");
        JLabel usernameLabel = new JLabel("  Username:");
        JLabel passwordLabel = new JLabel("  Password:");
        JLabel professionLabel = new JLabel("  Professione:");
        JLabel ageLabel = new JLabel("  Età:");
        JLabel residenzaLabel = new JLabel("  Residenza:");
        JLabel telefonoLabel = new JLabel("  Telefono:");
        JLabel puntoVenditaLabel = new JLabel("  Indirizzo del punto vendita:");

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        professionField = new JTextField(20);
        ageField = new JTextField(20);
        residenzaField = new JTextField(20);
        telefonoField = new JTextField(20);
        puntoVenditaField = new JTextField(20);

        JButton registerButton = new JButton("Registrati");
        JButton backButton = new JButton("Torna al login");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String profession = professionField.getText();
                String age = ageField.getText();

                // Qui puoi gestire la registrazione dei dati come preferisci
                // Ad esempio, puoi memorizzarli nel database o eseguire altre operazioni di registrazione

                // Visualizza i dati registrati
                String message = "Registrazione avvenuta con successo!\n\n" +
                        "Nome: " + firstName + "\n" +
                        "Cognome: " + lastName + "\n" +
                        "Email: " + email + "\n" +
                        "Username: " + username + "\n" +
                        "Professione: " + profession + "\n" +
                        "Data di Nascita: " + age;
                JOptionPane.showMessageDialog(RegistrationPanel.this, message);
            }
        });
        backButton.addActionListener(new BackToLoginListener(this.frame));

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
        contentPanel.add(professionLabel);
        contentPanel.add(professionField);
        contentPanel.add(ageLabel);
        contentPanel.add(ageField);
        contentPanel.add(residenzaLabel);
        contentPanel.add(residenzaField);
        contentPanel.add(telefonoLabel);
        contentPanel.add(telefonoField);
        contentPanel.add(puntoVenditaLabel);
        contentPanel.add(puntoVenditaField);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(backButton);
        contentPanel.add(registerButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }



}
