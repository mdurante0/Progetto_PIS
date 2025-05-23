package View.Listener;

import Business.PuntoVenditaBusiness;
import Business.Results.PuntoVenditaResult;
import Business.Results.RegisterResult;
import Business.UtenteBusiness;
import Model.Cliente;
import View.LoginPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegistrationListener implements ActionListener {

    private MainFrame frame;
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
    private Cliente cliente = new Cliente();

    public RegistrationListener(MainFrame frame, JTextField firstNameField, JTextField lastNameField, JTextField emailField, JTextField usernameField, JPasswordField passwordField, JPasswordField confermaPasswordField, JTextField professionField, JTextField ageField, JTextField residenzaField, JTextField telefonoField, JComboBox<String> puntoVenditaBox) {
        this.frame = frame;
        this.firstNameField = firstNameField;
        this.lastNameField = lastNameField;
        this.emailField = emailField;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.confermaPasswordField = confermaPasswordField;
        this.professionField = professionField;
        this.ageField = ageField;
        this.residenzaField = residenzaField;
        this.telefonoField = telefonoField;
        this.puntoVenditaBox = puntoVenditaBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String password = Arrays.toString(passwordField.getPassword());
        String confermaPassword = Arrays.toString(confermaPasswordField.getPassword());

        if(!password.equals(confermaPassword)){
            JOptionPane.showMessageDialog(this.frame, "Le password non coincidono! Riprova!");
        } else if (!telefonoField.getText().matches("\\d{10}"))
            JOptionPane.showMessageDialog(this.frame, "Formato numero di telefono errato! Riprova!");
        else if (!ageField.getText().matches("\\d{1,3}"))
            JOptionPane.showMessageDialog(this.frame, "Formato età errato! Riprova!");
        else if (puntoVenditaBox.getSelectedItem() == null)
            JOptionPane.showMessageDialog(this.frame, "Punto vendita non specificato! Riprova!");
        else {
            cliente.setTipo("CL");
            cliente.setName(firstNameField.getText());
            cliente.setSurname(lastNameField.getText());
            cliente.setEmail(emailField.getText());
            cliente.setUsername(usernameField.getText());
            cliente.setPwd(String.valueOf(passwordField.getPassword()));
            cliente.setAbilitazione(true);
            cliente.setProfessione(professionField.getText());
            cliente.setEta(Integer.parseInt(ageField.getText()));
            cliente.setResidenza(residenzaField.getText());
            cliente.setTelefono(telefonoField.getText());
            PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByNome(puntoVenditaBox.getSelectedItem().toString());
            if (puntoVenditaResult.getResult().equals(PuntoVenditaResult.Result.SALEPOINT_CARICATI))
                cliente.setPuntoVenditaDiRegistrazione(puntoVenditaResult.getPuntiVendita().get(0));

            RegisterResult registerResult = UtenteBusiness.getInstance().register(cliente);
            if(registerResult.getResult() == RegisterResult.Result.REGISTER_OK){
                this.frame.mostraPannelloAttuale(new LoginPanel(this.frame));
            }
            JOptionPane.showMessageDialog(this.frame, registerResult.getMessage());
        }
    }
}
