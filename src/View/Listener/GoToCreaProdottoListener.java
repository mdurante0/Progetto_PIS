package View.Listener;

import View.MainFrame;
import View.CreaProdottoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaProdottoListener implements ActionListener {
    private MainFrame frame;

    public GoToCreaProdottoListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaProdottoPanel(this.frame));
    }
}
