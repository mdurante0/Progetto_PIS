package View.Listener;

import Business.MagazzinoBusiness;
import Business.Results.MagazzinoResult;
import Model.Magazzino;
import View.MainFrame;
import View.MostraMagazziniPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveMagazzinoListener implements ActionListener {
    private MainFrame frame;
    private Magazzino magazzino;

    public RemoveMagazzinoListener(MainFrame frame, Magazzino magazzino) {
        this.frame = frame;
        this.magazzino = magazzino;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questo magazzino?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            MagazzinoResult result = MagazzinoBusiness.getInstance().removeMagazzino(magazzino);

            if(result.getResult().equals(MagazzinoResult.Result.DELETE_OK)){
                    this.frame.mostraPannelloAttuale(new MostraMagazziniPanel(this.frame));

            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
