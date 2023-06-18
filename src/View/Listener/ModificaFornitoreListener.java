package View.Listener;

import Business.FornitoreBusiness;
import Business.Results.FornitoreResult;
import Model.Fornitore;
import View.MainFrame;
import View.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModificaFornitoreListener implements ActionListener {

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
    private Fornitore fornitore = new Fornitore();

    public ModificaFornitoreListener(MainFrame frame, JTextField nomeField, JTextField emailField, JTextField telefonoField, JTextField nazioneField, JTextField cittaField, JTextField descrizioneField, JTextField sitoField) {
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

        fornitore.setNome(nomeField.getText());
        fornitore.setMail(emailField.getText());
        fornitore.setTelefono(telefonoField.getText());
        fornitore.setNazione(nazioneField.getText());
        fornitore.setCitta(cittaField.getText());
        fornitore.setDescrizione(descrizioneField.getText());
        fornitore.setSito(sitoField.getText());

        if (!fornitore.getNome().isEmpty() && !fornitore.getMail().isEmpty() && !fornitore.getDescrizione().isEmpty() && !fornitore.getSito().isEmpty()) {

            FornitoreResult fornitoreResult = FornitoreBusiness.getInstance().updateFornitore(fornitore);
            if (fornitoreResult.getResult() == FornitoreResult.Result.UPDATE_OK) {
                this.frame.mostraPannelloAttuale(new MenuPanel(this.frame));
            }
            JOptionPane.showMessageDialog(this.frame, fornitoreResult.getMessage());
        }
        JOptionPane.showMessageDialog(this.frame, "Attenzione, il fornitore deve avere almeno i seguenti campi: nome, email, descrizione, sito");
    }
}

