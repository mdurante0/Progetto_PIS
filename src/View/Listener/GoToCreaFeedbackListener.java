package View.Listener;

import Model.Articolo;
import View.CreaFeedbackPanel;
import View.CreaMagazzinoPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaFeedbackListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private String nomePuntoVendita;

    public GoToCreaFeedbackListener(MainFrame frame, Articolo articolo, String nomePuntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.nomePuntoVendita = nomePuntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaFeedbackPanel(this.frame, articolo,nomePuntoVendita));
    }
}
