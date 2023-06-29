package View.Listener;

import View.MainFrame;
import View.CreaServizioPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaServizioListener implements ActionListener {
    private MainFrame frame;

    public GoToCreaServizioListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaServizioPanel(this.frame));
    }
}
