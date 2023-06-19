package View.Listener;


import View.MainFrame;
import View.MostraMagazziniPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMostraMagazziniListener implements ActionListener {
    private MainFrame frame;

    public GoToMostraMagazziniListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MostraMagazziniPanel(this.frame));
    }
}
