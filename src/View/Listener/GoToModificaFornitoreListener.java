package View.Listener;

import Model.Fornitore;
import View.MainFrame;
import View.ModificaFornitorePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaFornitoreListener implements ActionListener {
    private MainFrame frame;
    private Fornitore produttore;
    public GoToModificaFornitoreListener(MainFrame frame, Fornitore p) {
        this.frame = frame;
        produttore = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaFornitorePanel(this.frame, produttore));
    }
}
