package View.Listener;

import View.CatalogoProdottiPanel;
import View.CatalogoServiziPanel;
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

    public GoToCatalogoListener(MainFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(nomePuntoVendita != null)
            this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, nomePuntoVendita));
        else
            this.frame.mostraPannelloAttuale(new CatalogoServiziPanel(this.frame));
    }
}
