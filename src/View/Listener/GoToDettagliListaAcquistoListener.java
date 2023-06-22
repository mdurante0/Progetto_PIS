package View.Listener;


import Model.ListaAcquisto;
import View.MainFrame;
import View.VisualizzaListaAcquistoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToDettagliListaAcquistoListener implements ActionListener {
    private MainFrame frame;
    private ListaAcquisto listaAcquisto;

    public GoToDettagliListaAcquistoListener(MainFrame frame, ListaAcquisto listaAcquisto) {
        this.frame = frame;
        this.listaAcquisto = listaAcquisto;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new VisualizzaListaAcquistoPanel(this.frame, listaAcquisto));
    }
}
