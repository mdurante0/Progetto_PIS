package View.Listener;

import View.MainFrame;
import View.ProduttorePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToProduttoreListener implements ActionListener {
    private MainFrame frame;
    public GoToProduttoreListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ProduttorePanel(this.frame));
    }
}
