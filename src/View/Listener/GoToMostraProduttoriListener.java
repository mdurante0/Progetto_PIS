package View.Listener;

import Model.Produttore;
import View.MainFrame;
import View.ModificaProduttorePanel;
import View.MostraProduttoriPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraProduttoriListener implements ActionListener {
    private MainFrame frame;
    private Produttore produttore;
    public GoToMostraProduttoriListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraProduttoriPanel(this.frame));
    }
}
