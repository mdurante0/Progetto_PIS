package View.Listener;

import Business.MailBusiness;
import Business.Results.MailResult;
import Model.Cliente;
import View.MainFrame;
import View.MostraClientiPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MailListener implements ActionListener {
    private MainFrame frame;
    private Cliente cliente;
    private JTextField oggettoField;
    private JTextArea testoArea;
    private static String allegatoPath;

    public MailListener(MainFrame frame, Cliente cliente, JTextField oggettoField, JTextArea testoArea) {
        this.frame = frame;
        this.cliente = cliente;
        this.oggettoField = oggettoField;
        this.testoArea = testoArea;
    }

    public static void setAllegatoPath(String allegatoPath) {
        MailListener.allegatoPath = allegatoPath;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String oggetto = oggettoField.getText();
        String testo = testoArea.getText();

        MailResult mailResult;
        if(allegatoPath == null)
            mailResult = MailBusiness.getInstance().invioEmail(cliente, oggetto, testo);
        else mailResult = MailBusiness.getInstance().invioEmail(cliente, oggetto, testo, allegatoPath);

        JOptionPane.showMessageDialog(this.frame, mailResult.getMessage());
        if(mailResult.getResult().equals(MailResult.Result.INVIO_OK))
            this.frame.mostraPannelloAttuale(new MostraClientiPanel(this.frame));
    }
}
