package View.Listener;

import Business.FeedbackBusiness;
import Business.Results.FeedbackResult;
import Model.*;
import Model.composite.ProdottoComposito;
import View.FeedbackPanel;
import View.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AggiungiFeedbackListener implements ActionListener {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JComboBox gradimentoField;
    private JTextArea commentoField;
    private Articolo articolo;
    private Cliente cliente;
    private PuntoVendita puntoVendita;
    private ProdottoComposito prodottoComposito;
    private Feedback feedback = new Feedback();

    public AggiungiFeedbackListener(MainFrame frame, JComboBox gradimentoField, JTextArea commentoField, Articolo articolo, Cliente cliente, PuntoVendita puntoVendita, ProdottoComposito prodottoComposito) {
        this.frame = frame;
        this.gradimentoField = gradimentoField;
        this.commentoField = commentoField;
        this.articolo = articolo;
        this.cliente = cliente;
        this.puntoVendita = puntoVendita;
        this.prodottoComposito = prodottoComposito;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gradimentoField.setModel(new DefaultComboBoxModel(Feedback.Punteggio.values()));
        feedback.setGradimento((Feedback.Punteggio) gradimentoField.getSelectedItem());
        feedback.setCommento(commentoField.toString());
        feedback.setData(new Date());
        feedback.setArticolo(articolo);

        FeedbackResult feedbackResult = FeedbackBusiness.getInstance().creaFeedback(feedback, cliente.getUsername(), articolo.getName() );
        if (feedbackResult.getResult() == FeedbackResult.Result.ADD_OK)
            this.frame.mostraPannelloAttuale(new FeedbackPanel(this.frame, articolo, puntoVendita, prodottoComposito));
        JOptionPane.showMessageDialog(this.frame, feedbackResult.getMessage());

    }
}

