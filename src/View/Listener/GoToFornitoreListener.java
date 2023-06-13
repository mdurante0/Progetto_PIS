package View.Listener;

import View.FornitorePanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToFornitoreListener implements ActionListener {
    private MainFrame frame;
    public GoToFornitoreListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new FornitorePanel(this.frame));
    }
}
