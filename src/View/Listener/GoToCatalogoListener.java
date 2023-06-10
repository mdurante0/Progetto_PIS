package View.Listener;

import View.CatalogoPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCatalogoListener implements ActionListener {
    private MainFrame frame;
    public GoToCatalogoListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CatalogoPanel(this.frame));
    }
}
