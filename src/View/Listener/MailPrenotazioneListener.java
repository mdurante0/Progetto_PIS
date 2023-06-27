package View.Listener;

import Business.MailBusiness;
import Business.PrenotazioneBusiness;
import Business.Results.MailResult;
import Business.Results.PrenotazioneResult;
import Model.Cliente;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MailPrenotazioneListener implements ActionListener {
    private MainFrame frame;
    private Cliente cliente;

    public MailPrenotazioneListener(MainFrame frame, Cliente cliente) {
        this.frame = frame;
        this.cliente = cliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PrenotazioneResult prenotazioneResult = PrenotazioneBusiness.getInstance().caricaPrenotazioniByUser(cliente.getUsername());

        if(prenotazioneResult.getResult().equals(PrenotazioneResult.Result.PRENOTAZIONI_CARICATE)){
            MailResult mailResult = MailBusiness.getInstance().invioPrenotazioni(prenotazioneResult.getPrenotazioni(), cliente);
            JOptionPane.showMessageDialog(this.frame, mailResult.getMessage());
        }
    }
}
