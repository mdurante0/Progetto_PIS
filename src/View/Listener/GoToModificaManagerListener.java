package View.Listener;

import Model.Manager;
import View.MainFrame;
import View.ModificaManagerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToModificaManagerListener implements ActionListener {
    private MainFrame frame;
    private Manager manager;
    public GoToModificaManagerListener(MainFrame frame, Manager p) {
        this.frame = frame;
        manager = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new ModificaManagerPanel(this.frame, manager));
    }
}
