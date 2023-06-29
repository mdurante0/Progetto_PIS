package View.Listener;

import Model.PuntoVendita;
import Model.composite.IProdotto;
import Model.composite.ProdottoComposito;
import View.DettagliComponentePanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToDettagliComponenteListener implements ActionListener {
    private MainFrame frame;
    private IProdotto prodotto;
    private ProdottoComposito prodottoComposito;
    private PuntoVendita puntoVendita;
    public GoToDettagliComponenteListener(MainFrame frame, IProdotto prodotto, ProdottoComposito prodottoComposito, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.prodotto = prodotto;
        this.prodottoComposito = prodottoComposito;
        this.puntoVendita = puntoVendita;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new DettagliComponentePanel(this.frame, prodotto, prodottoComposito, puntoVendita));
    }
}
