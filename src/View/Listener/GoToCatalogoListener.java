package View.Listener;

import View.CatalogoPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCatalogoListener implements ActionListener {
    private MainFrame frame;
    private String nomePuntoVendita;
    public GoToCatalogoListener(MainFrame frame, String nomePuntoVendita) {
        this.frame = frame;
        this.nomePuntoVendita = nomePuntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CatalogoPanel(this.frame, nomePuntoVendita));
    }
}
