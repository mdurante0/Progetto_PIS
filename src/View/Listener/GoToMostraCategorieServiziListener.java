package View.Listener;

import View.MainFrame;
import View.MostraCategoriaServizioPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraCategorieServiziListener implements ActionListener {
    private MainFrame frame;
    public GoToMostraCategorieServiziListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraCategoriaServizioPanel(this.frame));
    }
}
