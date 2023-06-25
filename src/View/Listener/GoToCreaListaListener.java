package View.Listener;

import Model.Articolo;
import Model.PuntoVendita;
import View.CreaListaAcquistoPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaListaListener implements ActionListener {
    private MainFrame frame;
    private JComboBox<Integer> quantitaBox;
    private Articolo articolo;
    private PuntoVendita puntoVendita;

    public GoToCreaListaListener(MainFrame frame, JComboBox<Integer> quantitaBox, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.quantitaBox = quantitaBox;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(quantitaBox != null)
            articolo.setQuantita(Integer.parseInt(quantitaBox.getSelectedItem().toString()));

        this.frame.mostraPannelloAttuale(new CreaListaAcquistoPanel(this.frame, articolo, puntoVendita));

    }
}
