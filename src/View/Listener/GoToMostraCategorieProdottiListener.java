package View.Listener;

import View.MainFrame;
import View.MostraCategoriaProdottoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraCategorieProdottiListener implements ActionListener {
    private MainFrame frame;
    public GoToMostraCategorieProdottiListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraCategoriaProdottoPanel(this.frame));
    }
}
