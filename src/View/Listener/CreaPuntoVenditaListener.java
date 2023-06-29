package View.Listener;

import Business.MagazzinoBusiness;
import Business.ManagerBusiness;
import Business.PuntoVenditaBusiness;
import Business.Results.MagazzinoResult;
import Business.Results.ManagerResult;
import Business.Results.PuntoVenditaResult;
import Model.PuntoVendita;
import View.MainFrame;
import View.MostraPuntiVenditaPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaPuntoVenditaListener implements ActionListener {
    private MainFrame frame;
    private JTextField nomeField;
    private JTextField indirizzoField;
    private JTextField telefonoField;
    private JTextField cittaField;
    private JComboBox managerBox;
    private JComboBox magazzinoBox;

    public CreaPuntoVenditaListener(MainFrame frame, JTextField nomeField, JTextField indirizzoField, JTextField telefonoField, JTextField cittaField, JComboBox managerBox, JComboBox magazzinoBox) {
        this.frame = frame;
        this.nomeField = nomeField;
        this.indirizzoField = indirizzoField;
        this.telefonoField = telefonoField;
        this.cittaField = cittaField;
        this.managerBox = managerBox;
        this.magazzinoBox = magazzinoBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PuntoVendita puntoVendita = new PuntoVendita();
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
        }

        //Caricamento Magazzino
        if (!magazzinoBox.getSelectedItem().toString().equals("Nessun Magazzino")) {
            MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByIndirizzo(magazzinoBox.getSelectedItem().toString());

            if (magazzinoResult.getResult().equals(MagazzinoResult.Result.MAGAZZINI_CARICATI))
                puntoVendita.setMagazzino(magazzinoResult.getMagazzini().get(0));
            else {
                JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());
                return;
            }
        }

        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().addSalePoint(puntoVendita);
        JOptionPane.showMessageDialog(this.frame, puntoVenditaResult.getMessage());
        if(puntoVenditaResult.getResult().equals(PuntoVenditaResult.Result.ADD_OK))
            this.frame.mostraPannelloAttuale(new MostraPuntiVenditaPanel(this.frame));
    }
}
