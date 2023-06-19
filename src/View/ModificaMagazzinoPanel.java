package View;

import Model.Magazzino;
import View.Listener.GoToMostraMagazziniListener;
import View.Listener.ModificaMagazzinoListener;

import javax.swing.*;
import java.awt.*;

public class ModificaMagazzinoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextField nomeField;
    private JTextField indirizzoField;
    private JTextField quantitaCorsieField;
    private JTextField quantitaScaffaliField;


    public ModificaMagazzinoPanel(MainFrame frame, Magazzino m) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Modifica Magazzino");
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

        indirizzoField = new JTextField(m.getIndirizzo(),20);
        quantitaCorsieField = new JTextField(String.valueOf(m.getQuantitaCorsie()),20);
        quantitaScaffaliField = new JTextField(String.valueOf(m.getQuantitaScaffali()),20);

        indirizzoField.setFont(bodyFont);
        quantitaCorsieField.setFont(bodyFont);
        quantitaScaffaliField.setFont(bodyFont);

        JButton modificaMagazzino = new JButton("Modifica");
        modificaMagazzino.addActionListener(new ModificaMagazzinoListener(this.frame, indirizzoField,quantitaCorsieField,quantitaScaffaliField));
        modificaMagazzino.setFont(bodyFont);
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
        contentPanel.add(modificaMagazzino);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
