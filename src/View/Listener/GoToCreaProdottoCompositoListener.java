package View.Listener;

import View.MainFrame;
import View.CreaProdottoCompositoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaProdottoCompositoListener implements ActionListener {
    private MainFrame frame;

    public GoToCreaProdottoCompositoListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaProdottoCompositoPanel(this.frame));
    }
}
