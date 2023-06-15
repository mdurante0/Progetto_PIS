package View;

import Business.MagazzinoBusiness;
import Business.ManagerBusiness;
import Business.Results.MagazzinoResult;
import Business.Results.ManagerResult;
import Model.Magazzino;
import Model.Manager;
import Model.PuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class ModificaPuntoVenditaPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nomeField;

    private JTextField indirizzoField;
    private JTextField telefonoField;
    private JTextField cittaField;
    private JComboBox managerBox;
    private JComboBox magazzinoBox;




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
        if(!result.getManagers().isEmpty()) {
            Iterator<Manager> iterator = result.getManagers().iterator();
            String[] nomiPV = new String[result.getManagers().size()];
            for (int i = 0; i < result.getManagers().size(); i++) {
                nomiPV[i] = iterator.next().getName();
            }
            managerBox= new JComboBox<>(nomiPV);
            managerBox.setFocusable(false);
            managerBox.setFont(bodyFont);
        }
        MagazzinoResult resultMagazzino = MagazzinoBusiness.getInstance().caricaMagazzini();
        if(!resultMagazzino.getMagazzini().isEmpty()) {
            Iterator<Magazzino> iterator = resultMagazzino.getMagazzini().iterator();
            String[] indirizziMPV = new String[resultMagazzino.getMagazzini().size()];
            for (int i = 0; i < resultMagazzino.getMagazzini().size(); i++) {
                indirizziMPV[i] = iterator.next().getIndirizzo();
            }
           magazzinoBox= new JComboBox<>(indirizziMPV);
           magazzinoBox.setFocusable(false);
           magazzinoBox.setFont(bodyFont);
        }



        nomeField = new JTextField(puntoVendita.getNome(),20);
        indirizzoField = new JTextField(puntoVendita.getIndirizzo(),20);
        telefonoField = new JTextField(puntoVendita.getTelefono(), 20);
        cittaField = new JTextField(puntoVendita.getCitta(), 20);



        nomeField.setFont(bodyFont);
        indirizzoField.setFont(bodyFont);
        telefonoField.setFont(bodyFont);
        cittaField.setFont(bodyFont);


        JButton aggiungiPuntoVenditaButton = new JButton("Modifica");
        aggiungiPuntoVenditaButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.setFont(bodyFont);

        // aggiungere gli action listener

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
        contentPanel.add(aggiungiPuntoVenditaButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
