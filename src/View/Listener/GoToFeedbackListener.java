package View.Listener;

import Model.Articolo;
import View.FeedbackPanelByPuntoVendita;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToFeedbackListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private String nomePuntoVendita;
    public GoToFeedbackListener(MainFrame frame, Articolo articolo, String nomePuntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.nomePuntoVendita = nomePuntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new FeedbackPanelByPuntoVendita(this.frame, articolo, nomePuntoVendita));
    }
}
