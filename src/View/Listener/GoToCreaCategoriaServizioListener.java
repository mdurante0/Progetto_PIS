package View.Listener;

import View.CreaCategoriaServizioPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaCategoriaServizioListener implements ActionListener {
    private MainFrame frame;
    public GoToCreaCategoriaServizioListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaCategoriaServizioPanel(this.frame));
    }
}
