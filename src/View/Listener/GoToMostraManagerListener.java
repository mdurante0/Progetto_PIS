package View.Listener;


import View.MainFrame;
import View.MostraManagerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraManagerListener implements ActionListener {
    private MainFrame frame;

    public GoToMostraManagerListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraManagerPanel(this.frame));
    }
}
