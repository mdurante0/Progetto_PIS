package View.Listener;

import Model.Articolo;
import Model.composite.ProdottoComposito;
import View.DettagliComponentePanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToDettagliComponenteListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private ProdottoComposito prodottoComposito;
    private String nomePuntoVendita;
    public GoToDettagliComponenteListener(MainFrame frame, Articolo articolo, ProdottoComposito prodottoComposito, String nomePuntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.prodottoComposito = prodottoComposito;
        this.nomePuntoVendita = nomePuntoVendita;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new DettagliComponentePanel(this.frame, articolo, prodottoComposito, nomePuntoVendita));
    }
}
