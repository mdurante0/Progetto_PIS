package View.Listener;

import View.CreaCategoriaServizioPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCategoriaServizioListener implements ActionListener {
    private MainFrame frame;
    public GoToCategoriaServizioListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaCategoriaServizioPanel(this.frame));
    }
}
