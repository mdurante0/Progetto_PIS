package View.Listener;

import View.MainFrame;
import View.MenuPuntiVenditaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMenuPuntiVenditaListener implements ActionListener {
    private MainFrame frame;
    public GoToMenuPuntiVenditaListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MenuPuntiVenditaPanel(this.frame));
    }
}
