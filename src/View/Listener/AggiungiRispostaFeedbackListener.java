package View.Listener;

import Business.FeedbackBusiness;
import Business.Results.FeedbackResult;
import Model.Articolo;
import Model.Cliente;
import Model.Feedback;
import Model.PuntoVendita;
import Model.composite.ProdottoComposito;
import View.FeedbackPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AggiungiRispostaFeedbackListener implements ActionListener {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private Articolo articolo;
    private PuntoVendita puntoVendita;
    private JTextArea rispostaField;
    ProdottoComposito prodottoComposito;

    private Feedback feedback ;

    public AggiungiRispostaFeedbackListener(MainFrame frame, JTextArea risposta, Articolo articolo,  PuntoVendita puntoVendita, ProdottoComposito prodottoComposito, Feedback feedback) {
        this.frame = frame;
        this.rispostaField = risposta;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
        this.prodottoComposito = prodottoComposito;
        this.feedback = feedback;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        feedback.setRisposta(rispostaField.getText());

        FeedbackResult feedbackResult = FeedbackBusiness.getInstance().rispondi(feedback, feedback.getRisposta());
        if (feedbackResult.getResult() == FeedbackResult.Result.UPDATE_OK)
            this.frame.mostraPannelloAttuale(new FeedbackPanel(this.frame, articolo, puntoVendita, prodottoComposito));
        JOptionPane.showMessageDialog(this.frame, feedbackResult.getMessage());

    }
}

