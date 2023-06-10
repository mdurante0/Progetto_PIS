package View.Listener;

import View.DettagliPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviousImageListener implements ActionListener {
    private DettagliPanel panel;
    public PreviousImageListener(DettagliPanel panel){
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            this.panel.previousImage();
    }
}
