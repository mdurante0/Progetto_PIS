package View.Listener;

import Model.PuntoVendita;
import View.MainFrame;
import View.ModificaPuntoVenditaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaPuntoVenditaListener implements ActionListener {
    private MainFrame frame;
    private PuntoVendita puntoVendita;
    public GoToModificaPuntoVenditaListener(MainFrame frame, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaPuntoVenditaPanel(this.frame, puntoVendita));
    }
}
