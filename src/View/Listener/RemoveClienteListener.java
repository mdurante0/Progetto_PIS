package View.Listener;

import Business.ClienteBusiness;
import Business.Results.ClienteResult;
import Model.Cliente;
import View.MainFrame;
import View.MostraClientiPanel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveClienteListener implements ActionListener {
    private MainFrame frame;
    private Cliente cliente;

    public RemoveClienteListener(MainFrame frame, Cliente cliente) {
        this.frame = frame;
        this.cliente = cliente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questo cliente?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            ClienteResult result = ClienteBusiness.getInstance().rimuoviCliente(cliente);

            if(result.getResult().equals(ClienteResult.Result.RIMOZIONE_OK)){
                    this.frame.mostraPannelloAttuale(new MostraClientiPanel(this.frame));

            }else JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
