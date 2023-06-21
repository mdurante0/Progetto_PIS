package View.Listener;


import Model.ListaAcquisto;
import View.DettagliListaAcquistoPanel;
import View.MainFrame;

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
        this.frame.mostraPannelloAttuale(new DettagliListaAcquistoPanel(this.frame, listaAcquisto));
    }
}
