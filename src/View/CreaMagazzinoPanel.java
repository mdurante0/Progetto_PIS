package View;

import Business.MagazzinoBusiness;
import Business.ManagerBusiness;
import Business.Results.MagazzinoResult;
import Business.Results.ManagerResult;
import Model.Magazzino;
import Model.Manager;
import View.Listener.CreaMagazzinoListener;
import View.Listener.GoToMenuListener;
import View.Listener.GoToMostraMagazziniListener;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class CreaMagazzinoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField indirizzoField;
    private JTextField quantitaCorsieField;
    private JTextField quantitaScaffaliField;


    public CreaMagazzinoPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Nuovo Magazzino");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(8,2));
        JLabel indirizzoLabel = new JLabel("  Indirizzo:");
        JLabel quantitaCorsieLabel = new JLabel("  Numero di corsie:");
        JLabel quantitaScaffaliLabel = new JLabel("  Numero di scaffali:");

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);

        indirizzoLabel.setFont(bodyFont);
        quantitaCorsieLabel.setFont(bodyFont);
        quantitaScaffaliLabel.setFont(bodyFont);

        indirizzoField = new JTextField(20);
        quantitaCorsieField = new JTextField(20);
        quantitaScaffaliField = new JTextField(20);

        indirizzoField.setFont(bodyFont);
        quantitaCorsieField.setFont(bodyFont);
        quantitaScaffaliField.setFont(bodyFont);

        JButton creaMagazzino = new JButton("Aggiungi");
        creaMagazzino.addActionListener(new CreaMagazzinoListener(this.frame, indirizzoField,quantitaCorsieField,quantitaScaffaliField));
        creaMagazzino.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMostraMagazziniListener(this.frame));
        tornaIndietroButton.setFont(bodyFont);

        contentPanel.add(quantitaCorsieLabel);
        contentPanel.add(quantitaCorsieField);
        contentPanel.add(quantitaScaffaliLabel);
        contentPanel.add(quantitaScaffaliField);
        contentPanel.add(indirizzoLabel);
        contentPanel.add(indirizzoField);

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(creaMagazzino);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
