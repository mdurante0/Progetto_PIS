package View.Listener;


import View.MainFrame;
import View.MostraFornitoriPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraFornitoriListener implements ActionListener {
    private MainFrame frame;

    public GoToMostraFornitoriListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraFornitoriPanel(this.frame));
    }
}
