package View.Listener;

import Model.Fornitore;
import View.MainFrame;
import View.ModificaFornitorePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaFornitoreListener implements ActionListener {
    private MainFrame frame;
    private Fornitore fornitore;
    public GoToModificaFornitoreListener(MainFrame frame, Fornitore fornitore) {
        this.frame = frame;
        this.fornitore = fornitore;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaFornitorePanel(this.frame, fornitore));
    }
}
