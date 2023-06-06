package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RegistrationPage extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField professionField;
    private JTextField birthDateField;

    public RegistrationPage() {
        setTitle("Pagina di Registrazione");
        setLayout(new GridLayout(8, 2));

        JLabel firstNameLabel = new JLabel("  Nome:");
        JLabel lastNameLabel = new JLabel("  Cognome:");
        JLabel emailLabel = new JLabel("  Email:");
        JLabel usernameLabel = new JLabel("  Username:");
        JLabel passwordLabel = new JLabel("  Password:");
        JLabel professionLabel = new JLabel("  Professione:");
        JLabel birthDateLabel = new JLabel("  Data di Nascita (YYYY-MM-DD):");

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        professionField = new JTextField(20);
        birthDateField = new JTextField(20);

        JButton registerButton = new JButton("Registrati");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String profession = professionField.getText();
                String birthDate = birthDateField.getText();

                // Qui puoi gestire la registrazione dei dati come preferisci
                // Ad esempio, puoi memorizzarli nel database o eseguire altre operazioni di registrazione

                // Visualizza i dati registrati
                String message = "Registrazione avvenuta con successo!\n\n" +
                        "Nome: " + firstName + "\n" +
                        "Cognome: " + lastName + "\n" +
                        "Email: " + email + "\n" +
                        "Username: " + username + "\n" +
                        "Professione: " + profession + "\n" +
                        "Data di Nascita: " + birthDate;
                JOptionPane.showMessageDialog(RegistrationPage.this, message);
            }
        });

        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(emailLabel);
        add(emailField);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(professionLabel);
        add(professionField);
        add(birthDateLabel);
        add(birthDateField);
        add(new JLabel()); // Spazio vuoto per allineamento
        add(registerButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }



}
