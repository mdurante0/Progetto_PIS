package View.Listener;

import Model.Magazzino;
import View.MainFrame;
import View.ModificaMagazzinoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaMagazzinoListener implements ActionListener {
    private MainFrame frame;
    private Magazzino magazzino;
    public GoToModificaMagazzinoListener(MainFrame frame, Magazzino p) {
        this.frame = frame;
        magazzino = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaMagazzinoPanel(this.frame, magazzino));
    }
}
