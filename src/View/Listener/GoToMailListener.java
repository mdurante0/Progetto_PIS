package View.Listener;

import Model.Cliente;
import View.MailPanel;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToMailListener implements ActionListener {
    private MainFrame frame;
    private Cliente cliente;
    public GoToMailListener(MainFrame frame, Cliente cliente) {
        this.frame = frame;
        this.cliente = cliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.mostraPannelloAttuale(new MailPanel(this.frame, cliente));
    }
}
