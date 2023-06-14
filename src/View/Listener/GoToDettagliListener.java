package View.Listener;

import Model.Articolo;
import View.DettagliPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToDettagliListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private String nomePuntoVendita;
    public GoToDettagliListener(MainFrame frame, Articolo articolo, String nomePuntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.nomePuntoVendita = nomePuntoVendita;
    }
    public GoToDettagliListener(MainFrame frame, Articolo articolo) {
        this.frame = frame;
        this.articolo = articolo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, articolo, nomePuntoVendita));
    }
}
