package View.Listener;


import View.CreaManagerPanel;
import View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToCreaManagerListener implements ActionListener {
    private MainFrame frame;

    public GoToCreaManagerListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new CreaManagerPanel(this.frame));
    }
}
