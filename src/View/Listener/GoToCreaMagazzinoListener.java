package View.Listener;

import View.CreaMagazzinoPanel;
import View.FornitorePanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaMagazzinoListener implements ActionListener {
    private MainFrame frame;
    public GoToCreaMagazzinoListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaMagazzinoPanel(this.frame));
    }
}
