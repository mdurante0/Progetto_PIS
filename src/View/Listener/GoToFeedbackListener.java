package View.Listener;

import Model.Articolo;
import Model.PuntoVendita;
import Model.composite.ProdottoComposito;
import View.FeedbackPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToFeedbackListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private PuntoVendita puntoVendita;
    private ProdottoComposito prodottoComposito;
    public GoToFeedbackListener(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
    }
    public GoToFeedbackListener(MainFrame frame, Articolo articolo, ProdottoComposito prodottoComposito, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.prodottoComposito = prodottoComposito;
        this.puntoVendita = puntoVendita;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new FeedbackPanel(this.frame, articolo, puntoVendita, prodottoComposito));
    }
}
