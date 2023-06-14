package View.Listener;

import View.MainFrame;
import View.NuovoProdottoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToNuovoProdottoListener implements ActionListener {
    private MainFrame frame;

    public GoToNuovoProdottoListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new NuovoProdottoPanel(this.frame));
    }
}
