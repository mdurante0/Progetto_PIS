package View.Listener;

import View.MainFrame;

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

    }
}
