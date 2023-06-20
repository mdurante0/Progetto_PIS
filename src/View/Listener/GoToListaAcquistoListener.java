package View.Listener;

import View.MainFrame;
import View.MostraListeAcquistoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToListaAcquistoListener implements ActionListener {
    private MainFrame frame;
    public GoToListaAcquistoListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraListeAcquistoPanel(this.frame));
    }
}
