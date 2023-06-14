package View.Listener;

import View.MainFrame;
import View.NuovoServizioPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToNuovoServizioListener implements ActionListener {
    private MainFrame frame;

    public GoToNuovoServizioListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new NuovoServizioPanel(this.frame));
    }
}
