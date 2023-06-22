package View.Listener;

import Business.ClienteBusiness;
import Business.Results.ClienteResult;
import Model.Cliente;
import View.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbilitazioneClienteListener implements ActionListener {
    private MainFrame frame;
    private Cliente cliente;
    private JButton abilitazione;

    public AbilitazioneClienteListener(MainFrame frame, Cliente cliente, JButton abilitazione) {
        this.frame = frame;
        this.cliente = cliente;
        this.abilitazione = abilitazione;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClienteResult result = new ClienteResult();
            if(cliente.getAbilitazione() == 1){
                int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler disabilitare il cliente "+ cliente.getUsername() +"?", "Confermi?", JOptionPane.YES_NO_OPTION);
                if (confirmed == 0){
                    result = ClienteBusiness.getInstance().abilitazioneCliente(cliente, false);
                    abilitazione.setText("Disabilitato");
                    frame.repaint();
                    frame.revalidate();
                }
            }
          else {
                int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler abilitare il cliente "+ cliente.getUsername() +"?", "Confermi?", JOptionPane.YES_NO_OPTION);
                if (confirmed == 0){
                    result = ClienteBusiness.getInstance().abilitazioneCliente(cliente, true);
                    abilitazione.setText("Abilitato");
                    frame.repaint();
                    frame.revalidate();
                }
            }
          JOptionPane.showMessageDialog(this.frame,result.getMessage());
    }
}
