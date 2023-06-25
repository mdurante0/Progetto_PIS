package View.Listener;

import View.CreaCategoriaProdottoPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaCategoriaProdottoListener implements ActionListener {
    private MainFrame frame;
    public GoToCreaCategoriaProdottoListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaCategoriaProdottoPanel(this.frame));
    }
}
