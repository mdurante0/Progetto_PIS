package View.Listener;

import View.MainFrame;
import View.MenuPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMenuListener implements ActionListener {
    private MainFrame frame;
    public GoToMenuListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MenuPanel(this.frame));
    }
}
