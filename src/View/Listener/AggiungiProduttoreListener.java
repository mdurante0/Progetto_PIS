package View.Listener;

import Business.ProduttoreBusiness;
import Business.Results.ProduttoreResult;
import Model.Produttore;
import View.LoginPanel;
import View.MainFrame;
import View.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AggiungiProduttoreListener implements ActionListener {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField telefonoField;
    private JTextField nazioneField;
    private JTextField cittaField;
    private JTextField descrizioneField;
    private JTextField sitoField;
    private Produttore produttore = new Produttore();

    public AggiungiProduttoreListener(MainFrame frame, JTextField nomeField, JTextField emailField, JTextField telefonoField, JTextField nazioneField, JTextField cittaField, JTextField descrizioneField, JTextField sitoField) {
        this.frame = frame;
        this.nomeField = nomeField;
        this.emailField = emailField;
        this.telefonoField = telefonoField;
        this.nazioneField = nazioneField;
        this.cittaField = cittaField;
        this.descrizioneField = descrizioneField;
        this.sitoField = sitoField;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        produttore.setNome(nomeField.getText());
        produttore.setMail(emailField.getText());
        produttore.setTelefono(telefonoField.getText());
        produttore.setNazione(nazioneField.getText());
        produttore.setCitta(cittaField.getText());
        produttore.setDescrizione(descrizioneField.getText());
        produttore.setSito(sitoField.getText());
        if (!produttore.getNome().isEmpty() && !produttore.getMail().isEmpty() && !produttore.getDescrizione().isEmpty() && !produttore.getSito().isEmpty()) {

            ProduttoreResult produttoreResult = ProduttoreBusiness.getInstance().addProduttore(produttore);
            if (produttoreResult.getResult() == ProduttoreResult.Result.ADD_OK) {
                this.frame.mostraPannelloAttuale(new MenuPanel(this.frame));
            }
            JOptionPane.showMessageDialog(this.frame, produttoreResult.getMessage());
        } else JOptionPane.showMessageDialog(this.frame, "Attenzione, il produttore deve avere almeno i seguenti campi: nome, email, descrizione, sito");

    }
}

