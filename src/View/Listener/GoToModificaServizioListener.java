package View.Listener;

import Model.Servizio;
import View.MainFrame;
import View.ModificaServizioPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaServizioListener implements ActionListener {
    private MainFrame frame;
    private Servizio servizio;
    public GoToModificaServizioListener(MainFrame frame, Servizio servizio) {
        this.frame = frame;
        this.servizio = servizio;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaServizioPanel(this.frame, servizio));
    }
}
