package View.Listener;

import Model.CategoriaProdotto;
import View.MainFrame;
import View.ModificaCategoriaProdottoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaCategoriaProdottoListener implements ActionListener {
    private MainFrame frame;
    private CategoriaProdotto categoriaProdotto;
    public GoToModificaCategoriaProdottoListener(MainFrame frame, CategoriaProdotto p) {
        this.frame = frame;
        categoriaProdotto = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaCategoriaProdottoPanel(this.frame, categoriaProdotto));
    }
}
