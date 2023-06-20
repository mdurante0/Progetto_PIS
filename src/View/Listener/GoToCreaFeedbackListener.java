package View.Listener;

import Model.Articolo;
import Model.PuntoVendita;
import View.CreaFeedbackPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaFeedbackListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private PuntoVendita puntoVendita;

    public GoToCreaFeedbackListener(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaFeedbackPanel(this.frame, articolo, puntoVendita));
    }
}
