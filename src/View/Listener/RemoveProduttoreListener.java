package View.Listener;

import Business.ProduttoreBusiness;
import Business.Results.ProduttoreResult;
import Model.Produttore;
import View.MainFrame;
import View.MostraProduttoriPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveProduttoreListener implements ActionListener {
    private MainFrame frame;
    private Produttore produttore;

    public RemoveProduttoreListener(MainFrame frame, Produttore produttore) {
        this.frame = frame;
        this.produttore = produttore;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questo produttore?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            ProduttoreResult result = ProduttoreBusiness.getInstance().removeProduttore(produttore);

            if(result.getResult().equals(ProduttoreResult.Result.DELETE_OK)){
                    this.frame.mostraPannelloAttuale(new MostraProduttoriPanel(this.frame));

            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
