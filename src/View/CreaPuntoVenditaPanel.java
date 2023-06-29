package View;

import Business.MagazzinoBusiness;
import Business.ManagerBusiness;
import Business.Results.MagazzinoResult;
import Business.Results.ManagerResult;
import Model.Magazzino;
import Model.Manager;
import View.Listener.CreaPuntoVenditaListener;
import View.Listener.GoToMostraPuntiVenditaListener;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class CreaPuntoVenditaPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nomeField;
    private JTextField indirizzoField;
    private JTextField telefonoField;
    private JTextField cittaField;
    private JComboBox managerBox;
    private JComboBox magazzinoBox;

    public CreaPuntoVenditaPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Nuovo Punto vendita");
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
            String[] nomiManager = new String[result.getManagers().size() + 1];
            for (int i = 0; i < result.getManagers().size(); i++) {
                nomiManager[i] = iterator.next().getName();
            }
            nomiManager[nomiManager.length - 1] = "Nessun Manager";
            managerBox= new JComboBox<>(nomiManager);
            managerBox.setFocusable(false);
            managerBox.setFont(bodyFont);
        }
        MagazzinoResult resultMagazzino = MagazzinoBusiness.getInstance().caricaMagazzini();
        if(!resultMagazzino.getMagazzini().isEmpty() ) {
            Iterator<Magazzino> iterator = resultMagazzino.getMagazzini().iterator();
            String[] indirizziMagazzini = new String[resultMagazzino.getMagazzini().size() + 1];
            for (int i = 0; i < resultMagazzino.getMagazzini().size(); i++) {
                indirizziMagazzini[i] = iterator.next().getIndirizzo();
            }
            indirizziMagazzini[indirizziMagazzini.length - 1] = "Nessun Magazzino";
            magazzinoBox= new JComboBox<>(indirizziMagazzini);
            magazzinoBox.setFocusable(false);
            magazzinoBox.setFont(bodyFont);
        }

        nomeField = new JTextField(20);
        indirizzoField = new JTextField(20);
        telefonoField = new JTextField(20);
        cittaField = new JTextField(20);

        nomeField.setFont(bodyFont);
        indirizzoField.setFont(bodyFont);
        telefonoField.setFont(bodyFont);
        cittaField.setFont(bodyFont);

        JButton aggiungiPuntoVenditaButton = new JButton("Aggiungi");
        // aggiungere gli action listener
        aggiungiPuntoVenditaButton.setFont(bodyFont);
        aggiungiPuntoVenditaButton.addActionListener(new CreaPuntoVenditaListener(this.frame, nomeField, indirizzoField, telefonoField, cittaField, managerBox, magazzinoBox));
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.setFont(bodyFont);
        tornaIndietroButton.addActionListener(new GoToMostraPuntiVenditaListener(this.frame));

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
