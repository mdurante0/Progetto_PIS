package View.Listener;

import Business.AbstractFactory.ICategoria;
import View.MainFrame;
import View.ModificaCategoriaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaCategoriaListener implements ActionListener {
    private MainFrame frame;
    private ICategoria categoria;
    public GoToModificaCategoriaListener(MainFrame frame, ICategoria categoria) {
        this.frame = frame;
        this.categoria = categoria;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaCategoriaPanel(this.frame, categoria));
    }
}
