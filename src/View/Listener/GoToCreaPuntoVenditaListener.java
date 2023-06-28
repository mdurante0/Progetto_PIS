package View.Listener;

import View.CreaPuntoVenditaPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaPuntoVenditaListener implements ActionListener {
    private MainFrame frame;
    public GoToCreaPuntoVenditaListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaPuntoVenditaPanel(this.frame));
    }
}
