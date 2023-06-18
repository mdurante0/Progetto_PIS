package View.Listener;

import Model.Produttore;
import View.MainFrame;
import View.ModificaProduttorePanel;
import View.ProduttorePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaProduttoreListener implements ActionListener {
    private MainFrame frame;
    private Produttore produttore;
    public GoToModificaProduttoreListener(MainFrame frame, Produttore p) {
        this.frame = frame;
        produttore = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaProduttorePanel(this.frame, produttore));
    }
}
