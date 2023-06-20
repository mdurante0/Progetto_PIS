package View.Listener;


import Model.Cliente;
import Model.PuntoVendita;
import View.MainFrame;
import View.MostraMagazziniPanel;
import View.MostraPrenotazioniPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraPrenotazioniListener implements ActionListener {
    private MainFrame frame;

    public GoToMostraPrenotazioniListener(MainFrame frame) {
        this.frame = frame;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraPrenotazioniPanel(this.frame));
    }
}
