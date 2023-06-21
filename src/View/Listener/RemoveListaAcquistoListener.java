package View.Listener;

import Business.ListaAcquistoBusiness;
import Business.Results.ListaAcquistoResult;
import Model.ListaAcquisto;
import View.MainFrame;
import View.MostraListeAcquistoPanel;
import View.MostraPrenotazioniPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveListaAcquistoListener implements ActionListener {
    private MainFrame frame;
   private ListaAcquisto listaAcquisto;

    public RemoveListaAcquistoListener(MainFrame frame, ListaAcquisto listaAcquisto) {
        this.frame = frame;
        this.listaAcquisto = listaAcquisto;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questa lista di acquisto?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            ListaAcquistoResult result = ListaAcquistoBusiness.getInstance().removeListaAcquisto(listaAcquisto);

            if(result.getResult().equals(ListaAcquistoResult.Result.REMOVE_OK)){
                    this.frame.mostraPannelloAttuale(new MostraListeAcquistoPanel(this.frame));

            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
