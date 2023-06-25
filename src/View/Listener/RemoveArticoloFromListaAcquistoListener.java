package View.Listener;

import Business.ListaAcquistoBusiness;
import Business.Results.ListaAcquistoResult;
import Model.Articolo;
import Model.ListaAcquisto;
import View.MainFrame;
import View.VisualizzaListaAcquistoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveArticoloFromListaAcquistoListener implements ActionListener {
    private MainFrame frame;
   private ListaAcquisto listaAcquisto;
   private Articolo articolo;

    public RemoveArticoloFromListaAcquistoListener(MainFrame frame, ListaAcquisto listaAcquisto, Articolo articolo) {
        this.frame = frame;
        this.listaAcquisto = listaAcquisto;
        this.articolo = articolo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler rimuovere questo articolo dalla lista?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            ListaAcquistoResult result = ListaAcquistoBusiness.getInstance().removeArticoloFromListaAcquisto(listaAcquisto, articolo.getName());

            if(result.getResult().equals(ListaAcquistoResult.Result.REMOVE_OK)){
                listaAcquisto.remove(articolo);
                this.frame.mostraPannelloAttuale(new VisualizzaListaAcquistoPanel(this.frame, listaAcquisto));

            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
