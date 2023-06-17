package View.Listener;

import View.MainFrame;
import View.NuovoProdottoCompositoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToNuovoProdottoCompositoListener implements ActionListener {
    private MainFrame frame;

    public GoToNuovoProdottoCompositoListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new NuovoProdottoCompositoPanel(this.frame));
    }
}
