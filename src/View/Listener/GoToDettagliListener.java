package View.Listener;

import Model.Articolo;
import Model.PuntoVendita;
import View.DettagliPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToDettagliListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private PuntoVendita puntoVendita;
    public GoToDettagliListener(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
    }
    public GoToDettagliListener(MainFrame frame, Articolo articolo) {
        this.frame = frame;
        this.articolo = articolo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, articolo, puntoVendita));
    }
}
