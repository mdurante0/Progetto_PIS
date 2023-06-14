package View.Listener;

import Business.UtenteBusiness;
import View.LoginPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToLoginListener implements ActionListener {
    private MainFrame frame;
    public final static String LOGOUT = "logout";
    public GoToLoginListener(MainFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = 0;
        if(e.getActionCommand().equals(LOGOUT))
            confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler effettuare il logout?", "Confermi?", JOptionPane.YES_NO_OPTION);

        if(confirmed == 0) {
            UtenteBusiness.getInstance().logout();
            this.frame.mostraPannelloAttuale(new LoginPanel(this.frame));
        }
    }
}
