package View.Listener;

import Model.PuntoVendita;
import View.CatalogoProdottiPanel;
import View.CatalogoServiziPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCatalogoListener implements ActionListener {
    private MainFrame frame;
    private PuntoVendita puntoVendita;
    public GoToCatalogoListener(MainFrame frame, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.puntoVendita = puntoVendita;
    }

    public GoToCatalogoListener(MainFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(puntoVendita != null)
            this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, puntoVendita));
        else
            this.frame.mostraPannelloAttuale(new CatalogoServiziPanel(this.frame));
    }
}
