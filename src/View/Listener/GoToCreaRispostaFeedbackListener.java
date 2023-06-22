package View.Listener;

import Model.Articolo;
import Model.PuntoVendita;
import View.CreaRispostaFeedbackProdottoPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaRispostaFeedbackListener implements ActionListener {
    private MainFrame frame;
    Articolo articolo;
    PuntoVendita puntoVendita;

    public GoToCreaRispostaFeedbackListener(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaRispostaFeedbackProdottoPanel(this.frame, articolo,puntoVendita));
    }
}
