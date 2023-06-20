package View.Listener;

import Business.PrenotazioneBusiness;
import Business.Results.PrenotazioneResult;
import Model.Prenotazione;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import View.MainFrame;
import View.MostraPrenotazioniPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePrenotazioneListener implements ActionListener {
    private MainFrame frame;
    private Prenotazione prenotazione;
    private IProdotto prodotto;

    public RemovePrenotazioneListener(MainFrame frame, Prenotazione produttore, IProdotto prodotto) {
        this.frame = frame;
        this.prenotazione = produttore;
        this.prodotto = prodotto;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questa prenotazione?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            PrenotazioneResult result = PrenotazioneBusiness.getInstance().removeProdottoInPrenotazione(prenotazione, prodotto);

            if(result.getResult().equals(PrenotazioneResult.Result.REMOVE_OK)){
                    this.frame.mostraPannelloAttuale(new MostraPrenotazioniPanel(this.frame));

            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
