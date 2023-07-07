package View.Listener;

import Business.Results.RegisterResult;
import Business.UtenteBusiness;
import Model.Manager;
import View.MainFrame;
import View.MostraManagerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AggiungiManagerListener implements ActionListener {

    private MainFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confermaPasswordField;
    private JTextField durataIncaricoField;
    private Manager manager = new Manager();

    public AggiungiManagerListener(MainFrame frame, JTextField firstNameField, JTextField lastNameField, JTextField emailField, JTextField usernameField, JPasswordField passwordField, JPasswordField confermaPasswordField, JTextField durataIncaricoField) {
        this.frame = frame;
        this.firstNameField = firstNameField;
        this.lastNameField = lastNameField;
        this.emailField = emailField;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.confermaPasswordField = confermaPasswordField;
        this.durataIncaricoField = durataIncaricoField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String password = Arrays.toString(passwordField.getPassword());
        String confermaPassword = Arrays.toString(confermaPasswordField.getPassword());

        if(!password.equals(confermaPassword)){
            JOptionPane.showMessageDialog(this.frame, "Le password non coincidono! Riprova!");
        }
        else {
            manager.setTipo("MN");
            manager.setName(firstNameField.getText());
            manager.setSurname(lastNameField.getText());
            manager.setEmail(emailField.getText());
            manager.setUsername(usernameField.getText());
            manager.setPwd(String.valueOf(passwordField.getPassword()));
            manager.setDurataIncarico(Integer.parseInt(String.valueOf(durataIncaricoField.getText())));

            RegisterResult registerResult = UtenteBusiness.getInstance().register(manager);
            if(registerResult.getResult() == RegisterResult.Result.REGISTER_OK){
                this.frame.mostraPannelloAttuale(new MostraManagerPanel(this.frame));
            }
            JOptionPane.showMessageDialog(this.frame, registerResult.getMessage());
        }
    }
}
