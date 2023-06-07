package View.Listener;

import Business.Encrypt;
import Business.Results.LoginResult;
import Business.UtenteBusiness;
import View.CatalogoPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {
    private MainFrame frame;
    private JTextField username;
    private JPasswordField password;

    public LoginListener(JTextField username, JPasswordField password, MainFrame frame) {
        this.username = username;
        this.password = password;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String user = username.getText();
        String pwd = new String(password.getPassword());

        System.out.println("username: " + user);
        System.out.println("password: " + Encrypt.encrypt(pwd));
        System.out.println("------------");

        LoginResult result = UtenteBusiness.getInstance().login(user, pwd);
        if(result.getResult() == LoginResult.Result.LOGIN_OK) {
            frame.mostraPannelloAttuale(new CatalogoPanel());
            //refresh view dei pulsanti
            //frame.aggiornaMenuPulsanti();
        }
        else
            JOptionPane.showMessageDialog(null, result.getMessage());
    }

}
