package View.Listener;


import View.MainFrame;
import View.MostraClientiPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraClientiListener implements ActionListener {
    private MainFrame frame;

    public GoToMostraClientiListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraClientiPanel(this.frame));
    }
}
