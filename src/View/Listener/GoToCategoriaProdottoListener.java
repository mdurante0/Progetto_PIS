package View.Listener;

import View.CreaCategoriaProdottoPanel;
import View.FornitorePanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCategoriaProdottoListener implements ActionListener {
    private MainFrame frame;
    public GoToCategoriaProdottoListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaCategoriaProdottoPanel(this.frame));
    }
}
