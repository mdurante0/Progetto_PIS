package View.Listener;

import Business.ManagerBusiness;
import Business.Results.ManagerResult;
import Model.Manager;
import View.MainFrame;
import View.MostraManagerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveManagerListener implements ActionListener {
    private MainFrame frame;
    private Manager manager;
    public RemoveManagerListener(MainFrame frame, Manager manager) {
        this.frame = frame;
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questo manager?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            ManagerResult result = ManagerBusiness.getInstance().removeManager(manager);

            if(result.getResult().equals(ManagerResult.Result.RIMOZIONE_OK)){
                    this.frame.mostraPannelloAttuale(new MostraManagerPanel(this.frame));
            }else JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
