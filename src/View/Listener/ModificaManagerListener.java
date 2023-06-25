package View.Listener;

import Business.ManagerBusiness;
import Business.Results.ManagerResult;
import Model.Manager;
import View.MainFrame;
import View.MostraManagerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


public class ModificaManagerListener implements ActionListener {

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
    private Manager manager;

    public ModificaManagerListener(MainFrame frame, JTextField firstNameField, JTextField lastNameField, JTextField emailField, JTextField usernameField, JPasswordField passwordField, JPasswordField confermaPasswordField, JTextField durataIncaricoField, Manager manager) {
        this.frame = frame;
        this.firstNameField = firstNameField;
        this.lastNameField = lastNameField;
        this.emailField = emailField;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.confermaPasswordField = confermaPasswordField;
        this.durataIncaricoField = durataIncaricoField;
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String password = Arrays.toString(passwordField.getPassword());
        String confermaPassword = Arrays.toString(confermaPasswordField.getPassword());

        if (!password.equals(confermaPassword)) {
            JOptionPane.showMessageDialog(this.frame, "Le password non coincidono! Riprova!");
            return;
        }

        manager.setName(firstNameField.getText());
        manager.setSurname(lastNameField.getText());
        manager.setEmail(emailField.getText());
        manager.setUsername(usernameField.getText());
        manager.setPwd(String.valueOf(passwordField.getPassword()));
        manager.setDurataIncarico(Integer.parseInt(durataIncaricoField.getText()));
        if (!manager.getName().isEmpty() && !manager.getSurname().isEmpty() && !manager.getEmail().isEmpty() && !manager.getUsername().isEmpty()) {
            ManagerResult managerResult = ManagerBusiness.getInstance().updateManager(manager);
            if (managerResult.getResult() == ManagerResult.Result.UPDATE_OK) {
                this.frame.mostraPannelloAttuale(new MostraManagerPanel(this.frame));
            }
            JOptionPane.showMessageDialog(this.frame, managerResult.getMessage());
        } else {
            JOptionPane.showMessageDialog(this.frame, "Attenzione, il Manager deve avere almeno i seguenti campi: nome, cognome, email, username");
        }

    }
}


