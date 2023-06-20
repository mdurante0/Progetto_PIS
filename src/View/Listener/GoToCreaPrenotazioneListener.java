package View.Listener;

import Model.PuntoVendita;
import View.CreaPrenotazionePanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaPrenotazioneListener implements ActionListener {
    private MainFrame frame;

    public GoToCreaPrenotazioneListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaPrenotazionePanel(this.frame));
    }
}
