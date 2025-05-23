package View.Listener;

import Model.PuntoVendita;
import Model.composite.ProdottoComposito;
import View.MainFrame;
import View.MostraComponentiPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraComponentiListener implements ActionListener {

    private MainFrame frame;
    private ProdottoComposito prodottoComposito;
    private PuntoVendita puntoVendita;
    public GoToMostraComponentiListener(MainFrame frame, ProdottoComposito prodottoComposito, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.prodottoComposito = prodottoComposito;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.mostraPannelloAttuale(new MostraComponentiPanel(frame, prodottoComposito, puntoVendita));
    }
}
