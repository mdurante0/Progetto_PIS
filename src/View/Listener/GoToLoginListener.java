package View.Listener;

import View.LoginPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToLoginListener implements ActionListener {
    private MainFrame frame;
    public GoToLoginListener(MainFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new LoginPanel(this.frame));
    }
}
