package View.Listener;

import Business.MagazzinoBusiness;
import Business.ManagerBusiness;
import Business.PuntoVenditaBusiness;
import Business.Results.MagazzinoResult;
import Business.Results.ManagerResult;
import Business.Results.PuntoVenditaResult;
import Model.Magazzino;
import Model.Manager;
import Model.PuntoVendita;
import View.MainFrame;
import View.MostraPuntiVenditaPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificaPuntoVenditaListener implements ActionListener {
    private MainFrame frame;
    private JTextField nomeField;
    private JTextField indirizzoField;
    private JTextField telefonoField;
    private JTextField cittaField;
    private JComboBox<String> managerBox;
    private JComboBox<String> magazzinoBox;
    private PuntoVendita puntoVendita;

    public ModificaPuntoVenditaListener(MainFrame frame, JTextField nomeField, JTextField indirizzoField, JTextField telefonoField, JTextField cittaField, JComboBox<String> managerBox, JComboBox<String> magazzinoBox, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.nomeField = nomeField;
        this.indirizzoField = indirizzoField;
        this.telefonoField = telefonoField;
        this.cittaField = cittaField;
        this.managerBox = managerBox;
        this.magazzinoBox = magazzinoBox;
        this.puntoVendita = puntoVendita;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        puntoVendita.setNome(nomeField.getText());
        puntoVendita.setIndirizzo(indirizzoField.getText());
        puntoVendita.setTelefono(telefonoField.getText());
        puntoVendita.setCitta(cittaField.getText());

        //Caricamento Manager
        if(!managerBox.getSelectedItem().toString().equals("Nessun Manager")){
            ManagerResult managerResult = ManagerBusiness.getInstance().caricaManagerByUsername(managerBox.getSelectedItem().toString());

            if(managerResult.getResult().equals(ManagerResult.Result.MANAGER_CARICATI))
                puntoVendita.setManager(managerResult.getManagers().get(0));
            else {
                JOptionPane.showMessageDialog(this.frame, managerResult.getMessage());
                return;
            }
        } else puntoVendita.setManager(new Manager());

        //Caricamento Magazzino
        if (!magazzinoBox.getSelectedItem().toString().equals("Nessun Magazzino")) {
            MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByIndirizzo(magazzinoBox.getSelectedItem().toString());

            if (magazzinoResult.getResult().equals(MagazzinoResult.Result.MAGAZZINI_CARICATI))
                puntoVendita.setMagazzino(magazzinoResult.getMagazzini().get(0));
            else {
                JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());
                return;
            }
        } else puntoVendita.setMagazzino(new Magazzino());

        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().updateSalePoint(puntoVendita);
        JOptionPane.showMessageDialog(this.frame, puntoVenditaResult.getMessage());
        if(puntoVenditaResult.getResult().equals(PuntoVenditaResult.Result.UPDATE_SALEPOINT_OK))
            this.frame.mostraPannelloAttuale(new MostraPuntiVenditaPanel(this.frame));
    }
}
