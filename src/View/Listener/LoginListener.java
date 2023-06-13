package View.Listener;

import Business.Results.LoginResult;
import Business.UtenteBusiness;
import View.MainFrame;
import View.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {
    private MainFrame frame;
    private JTextField username;
    private JPasswordField password;

    public LoginListener(JTextField username, JPasswordField password, MainFrame frame) {
        this.frame = frame;
        this.username = username;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String user = username.getText();
        String pwd = new String(password.getPassword());

        LoginResult result = UtenteBusiness.getInstance().login(user, pwd);
        if(result.getResult() == LoginResult.Result.LOGIN_OK) {
            frame.mostraPannelloAttuale(new MenuPanel(this.frame));
        }
        JOptionPane.showMessageDialog(this.frame, result.getMessage());
    }

}
