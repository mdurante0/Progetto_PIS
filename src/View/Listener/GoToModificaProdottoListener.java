package View.Listener;

import Model.PuntoVendita;
import Model.composite.Prodotto;
import View.MainFrame;
import View.ModificaProdottoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaProdottoListener implements ActionListener {
    private MainFrame frame;
    private Prodotto prodotto;
    private PuntoVendita puntoVendita;
    public GoToModificaProdottoListener(MainFrame frame, Prodotto prodotto, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.prodotto = prodotto;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaProdottoPanel(this.frame, prodotto, puntoVendita));
    }
}
