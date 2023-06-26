package View.Listener;


import View.MainFrame;
import View.MostraPuntiVenditaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraPuntiVenditaListener implements ActionListener {
    private MainFrame frame;

    public GoToMostraPuntiVenditaListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraPuntiVenditaPanel(this.frame));
    }
}
