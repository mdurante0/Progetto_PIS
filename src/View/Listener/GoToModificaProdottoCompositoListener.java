package View.Listener;

import Model.PuntoVendita;
import Model.composite.ProdottoComposito;
import View.MainFrame;
import View.ModificaProdottoCompositoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaProdottoCompositoListener implements ActionListener {
    private MainFrame frame;
    private ProdottoComposito prodottoComposito;
    private PuntoVendita puntoVendita;
    public GoToModificaProdottoCompositoListener(MainFrame frame, ProdottoComposito prodottoComposito, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.prodottoComposito = prodottoComposito;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaProdottoCompositoPanel(this.frame, prodottoComposito, puntoVendita));
    }
}
