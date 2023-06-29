package View.Listener;

import Business.PuntoVenditaBusiness;
import Business.Results.PuntoVenditaResult;
import Model.PuntoVendita;
import View.MainFrame;
import View.MostraManagerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePuntoVenditaListener implements ActionListener {
    private MainFrame frame;
    private PuntoVendita puntoVendita;
    public RemovePuntoVenditaListener(MainFrame frame, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questo punto vendita?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            PuntoVenditaResult result = PuntoVenditaBusiness.getInstance().removeSalePoint(puntoVendita);

            if(result.getResult().equals(PuntoVenditaResult.Result.DELETE_OK)){
                    this.frame.mostraPannelloAttuale(new MostraManagerPanel(this.frame));
            }else JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
