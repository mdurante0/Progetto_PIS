package View.Listener;

import Model.Articolo;
import Model.composite.ProdottoComposito;
import View.FeedbackPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToFeedbackListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private String nomePuntoVendita;
    private ProdottoComposito prodottoComposito;
    public GoToFeedbackListener(MainFrame frame, Articolo articolo, String nomePuntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.nomePuntoVendita = nomePuntoVendita;
    }
    public GoToFeedbackListener(MainFrame frame, Articolo articolo, ProdottoComposito prodottoComposito, String nomePuntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.prodottoComposito = prodottoComposito;
        this.nomePuntoVendita = nomePuntoVendita;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new FeedbackPanel(this.frame, articolo, nomePuntoVendita, prodottoComposito));
    }
}
