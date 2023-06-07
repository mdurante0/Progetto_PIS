package View.Listener;

import View.LoginPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackToLoginListener implements ActionListener {
    private MainFrame frame;

    public BackToLoginListener(MainFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.mostraPannelloAttuale(new LoginPanel(this.frame));
    }
}
