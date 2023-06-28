package View.Listener;

import Business.ListaAcquistoBusiness;
import Business.Results.ListaAcquistoResult;
import Model.Articolo;
import Model.ListaAcquisto;
import Model.PuntoVendita;
import Model.composite.IProdotto;
import View.DettagliPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggiungiAListaListener implements ActionListener {
    private MainFrame frame;
    private ListaAcquisto listaAcquisto;
    private Articolo articolo;
    private PuntoVendita puntoVendita;

    public AggiungiAListaListener(MainFrame frame, ListaAcquisto listaAcquisto, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.listaAcquisto = listaAcquisto;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ListaAcquistoResult listaAcquistoResult;

        if(articolo instanceof IProdotto) {
            for (int i = 0; i < listaAcquisto.getArticoli().size(); i++) {
                Articolo articoloLista = listaAcquisto.getArticoli().get(i);

                if (articoloLista.getName().equals(articolo.getName())) {

                    int quantitaTotale = articoloLista.getQuantita() + articolo.getQuantita();
                    listaAcquisto.getArticoli().get(i).setQuantita(quantitaTotale);
                    listaAcquistoResult = ListaAcquistoBusiness.getInstance().updateListaAcquisto(listaAcquisto);
                    JOptionPane.showMessageDialog(this.frame, listaAcquistoResult.getMessage());
                    if (listaAcquistoResult.getResult().equals(ListaAcquistoResult.Result.UPDATE_OK))
                        this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, articolo, puntoVendita));
                    return;
                }
            }
        }

        listaAcquistoResult = ListaAcquistoBusiness.getInstance().addArticoloInListaAcquisto(listaAcquisto, articolo);
        JOptionPane.showMessageDialog(this.frame, listaAcquistoResult.getMessage());
        if(listaAcquistoResult.getResult().equals(ListaAcquistoResult.Result.ADD_OK))
            this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, articolo, puntoVendita));
    }
}
