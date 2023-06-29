package View;

import Business.MagazzinoBusiness;
import Business.ManagerBusiness;
import Business.Results.MagazzinoResult;
import Business.Results.ManagerResult;
import Model.Magazzino;
import Model.Manager;
import Model.PuntoVendita;
import View.Listener.GoToMostraPuntiVenditaListener;
import View.Listener.ModificaPuntoVenditaListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModificaPuntoVenditaPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nomeField;
    private JTextField indirizzoField;
    private JTextField telefonoField;
    private JTextField cittaField;
    private JComboBox<String> managerBox;
    private JComboBox<String> magazzinoBox;

    public ModificaPuntoVenditaPanel(MainFrame frame, PuntoVendita puntoVendita) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Modifica Punto vendita");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(8,2));
        JLabel firstNameLabel = new JLabel("  Nome:");
        JLabel telefonoLabel = new JLabel("  Telefono");
        JLabel cittaLabel = new JLabel("  Città:");
        JLabel indirizzoLabel = new JLabel("  Indirizzo:");
        JLabel managerLabel = new JLabel("  Manager:");
        JLabel magazzinoLabel = new JLabel("  Magazzino");

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        firstNameLabel.setFont(bodyFont);
        telefonoLabel.setFont(bodyFont);
        cittaLabel.setFont(bodyFont);
        indirizzoLabel.setFont(bodyFont);
        managerLabel.setFont(bodyFont);
        magazzinoLabel.setFont(bodyFont);

        ManagerResult result = ManagerBusiness.getInstance().caricaManagers();
        ArrayList<Manager> managers = result.getManagers();
        if(!result.getManagers().isEmpty()) {
            String[] usernameManagers = new String[managers.size()+1];
            for (int i = 0; i < managers.size(); i++) {
                usernameManagers[i] = managers.get(i).getUsername();
            }
            usernameManagers[usernameManagers.length-1]="Nessun Manager";
            managerBox= new JComboBox<>(usernameManagers);
            managerBox.setFocusable(false);
            managerBox.setFont(bodyFont);
            if (puntoVendita.getManager().getUsername() != null)
                managerBox.setSelectedItem(puntoVendita.getManager().getUsername());
            else managerBox.setSelectedItem("Nessun Manager");
        }

        MagazzinoResult resultMagazzino = MagazzinoBusiness.getInstance().caricaMagazzini();
        ArrayList<Magazzino> magazzini = resultMagazzino.getMagazzini();
        if(!resultMagazzino.getMagazzini().isEmpty()) {
            String[] indirizziMPV = new String[magazzini.size()+1];
            for (int i = 0; i < magazzini.size(); i++) {
                indirizziMPV[i] = magazzini.get(i).getIndirizzo();
            }
            indirizziMPV[indirizziMPV.length-1] = "Nessun Magazzino";
            magazzinoBox= new JComboBox<>(indirizziMPV);
            magazzinoBox.setFocusable(false);
            magazzinoBox.setFont(bodyFont);
            if(puntoVendita.getMagazzino().getIndirizzo() != null)
                magazzinoBox.setSelectedItem(puntoVendita.getMagazzino().getIndirizzo());
            else magazzinoBox.setSelectedItem("Nessun Magazzino");
        }

        nomeField = new JTextField(puntoVendita.getNome(),20);
        indirizzoField = new JTextField(puntoVendita.getIndirizzo(),20);
        telefonoField = new JTextField(puntoVendita.getTelefono(), 20);
        cittaField = new JTextField(puntoVendita.getCitta(), 20);

        nomeField.setFont(bodyFont);
        indirizzoField.setFont(bodyFont);
        telefonoField.setFont(bodyFont);
        cittaField.setFont(bodyFont);

        JButton modificaPuntoVenditaButton = new JButton("Modifica");
        modificaPuntoVenditaButton.addActionListener(new ModificaPuntoVenditaListener(this.frame, nomeField, indirizzoField, telefonoField, cittaField, managerBox, magazzinoBox, puntoVendita));
        modificaPuntoVenditaButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMostraPuntiVenditaListener(this.frame));
        tornaIndietroButton.setFont(bodyFont);

        contentPanel.add(firstNameLabel);
        contentPanel.add(nomeField);
        contentPanel.add(telefonoLabel);
        contentPanel.add(telefonoField);
        contentPanel.add(cittaLabel);
        contentPanel.add(cittaField);
        contentPanel.add(indirizzoLabel);
        contentPanel.add(indirizzoField);
        contentPanel.add(managerLabel);
        contentPanel.add(managerBox);
        contentPanel.add(magazzinoLabel);
        contentPanel.add(magazzinoBox);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(modificaPuntoVenditaButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }
}
