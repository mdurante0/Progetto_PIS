package View.Listener;

import Model.Articolo;
import Model.Feedback;
import Model.PuntoVendita;
import Model.composite.ProdottoComposito;
import View.CreaRispostaFeedbackProdottoPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaRispostaFeedbackListener implements ActionListener {
    private MainFrame frame;
   private Articolo articolo;
   private PuntoVendita puntoVendita;
   private ProdottoComposito prodottoComposito;
   private Feedback feedback;

    public GoToCreaRispostaFeedbackListener(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita, ProdottoComposito prodottoComposito, Feedback feedback) {
        this.frame = frame;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
        this.prodottoComposito = prodottoComposito;
        this.feedback = feedback;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaRispostaFeedbackProdottoPanel(this.frame, articolo,puntoVendita, prodottoComposito, feedback));
    }
}
