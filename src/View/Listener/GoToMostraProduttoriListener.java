package View.Listener;


import View.MainFrame;
import View.MostraProduttoriPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraProduttoriListener implements ActionListener {
    private MainFrame frame;

    public GoToMostraProduttoriListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraProduttoriPanel(this.frame));
    }
}
