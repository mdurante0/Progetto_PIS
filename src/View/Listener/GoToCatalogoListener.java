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
    public static final String PRODOTTI = "prodotti";
    public static final String SERVIZI = "servizi";
    public GoToCatalogoListener(MainFrame frame, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.puntoVendita = puntoVendita;
    }

    public GoToCatalogoListener(MainFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(puntoVendita != null && e.getActionCommand().equals(PRODOTTI))
            this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, puntoVendita));
        else if (e.getActionCommand().equals(SERVIZI))
            this.frame.mostraPannelloAttuale(new CatalogoServiziPanel(this.frame));
        else this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, null));
    }
}
