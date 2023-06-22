package View.Listener;

import Model.Articolo;
import Model.Cliente;
import Model.PuntoVendita;
import Model.composite.ProdottoComposito;
import View.CreaFeedbackPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaFeedbackListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private PuntoVendita puntoVendita;
    private Cliente cliente;
    private ProdottoComposito prodottoComposito;

    public GoToCreaFeedbackListener(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita, Cliente cliente, ProdottoComposito prodottoComposito) {
        this.frame = frame;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
        this.cliente = cliente;
        this.prodottoComposito = prodottoComposito;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaFeedbackPanel(this.frame, articolo, puntoVendita, cliente, prodottoComposito));
    }
}
