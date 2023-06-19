package View.Listener;

import Business.FornitoreBusiness;
import Business.Results.FornitoreResult;
import Model.Fornitore;
import View.MainFrame;
import View.MostraFornitoriPanel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveFornitoreListener implements ActionListener {
    private MainFrame frame;
    private Fornitore fornitore;

    public RemoveFornitoreListener(MainFrame frame, Fornitore fornitore) {
        this.frame = frame;
        this.fornitore = fornitore;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questo fornitore?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            FornitoreResult result = FornitoreBusiness.getInstance().removeFornitore(fornitore);

            if(result.getResult().equals(FornitoreResult.Result.DELETE_OK)){
                    this.frame.mostraPannelloAttuale(new MostraFornitoriPanel(this.frame));

            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
