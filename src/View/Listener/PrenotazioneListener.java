package View.Listener;

import Business.PrenotazioneBusiness;
import Business.Results.PrenotazioneResult;
import Business.SessionManager;
import Model.Prenotazione;
import Model.Utente;
import Model.composite.IProdotto;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Date;

public class PrenotazioneListener implements ActionListener {
    private MainFrame frame;
    private IProdotto prodotto;
    private JTextField quantitaPrenotazioneField;

    public PrenotazioneListener(MainFrame frame, IProdotto prodotto, JTextField quantitaPrenotazioneField) {
        this.frame = frame;
        this.prodotto = prodotto;
        this.quantitaPrenotazioneField = quantitaPrenotazioneField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Integer.parseInt(quantitaPrenotazioneField.getText());
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(frame, "Verificare i valori inseriti");
            return;
        }
        if (Integer.parseInt(quantitaPrenotazioneField.getText()) <= 0) {
            JOptionPane.showMessageDialog(frame, "Verificare i valori inseriti");
            return;
        }

        prodotto.setQuantita(Integer.parseInt(quantitaPrenotazioneField.getText()));

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        Prenotazione prenotazione = new Prenotazione(prodotto, Date.from(Instant.now()), u.getIdUtente());

        PrenotazioneResult prenotazioneResult = PrenotazioneBusiness.getInstance().addPrenotazione(prenotazione);
        JOptionPane.showMessageDialog(frame, prenotazioneResult.getMessage());
        quantitaPrenotazioneField.setText("");
    }
}
