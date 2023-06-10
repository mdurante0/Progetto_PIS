package View.Listener;

import View.MainFrame;
import View.RegistrationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToRegistrationListener implements ActionListener {

    private MainFrame frame;

    public GoToRegistrationListener(MainFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.mostraPannelloAttuale(new RegistrationPanel(this.frame));
    }
}
