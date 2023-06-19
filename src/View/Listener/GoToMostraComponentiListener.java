package View.Listener;

import Model.composite.ProdottoComposito;
import View.MainFrame;
import View.MostraComponentiPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraComponentiListener implements ActionListener {

    private MainFrame frame;
    private ProdottoComposito prodottoComposito;
    private String nomePuntoVendita;
    public GoToMostraComponentiListener(MainFrame frame, ProdottoComposito prodottoComposito, String nomePuntoVendita) {
        this.frame = frame;
        this.prodottoComposito = prodottoComposito;
        this.nomePuntoVendita = nomePuntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.mostraPannelloAttuale(new MostraComponentiPanel(frame, prodottoComposito, nomePuntoVendita));
    }
}
