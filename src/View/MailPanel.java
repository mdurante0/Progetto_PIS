package View;

import Model.Cliente;
import View.Listener.GoToMostraClientiListener;
import View.Listener.MailListener;
import View.Listener.SfogliaListener;

import javax.swing.*;
import java.awt.*;

public class MailPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    public MailPanel(MainFrame frame, Cliente cliente) {
        this.frame = frame;
        this.setLayout(new BorderLayout());
        titlePanel.setLayout(new FlowLayout());
        contentPanel.setLayout(new GridLayout(0,2));
        southPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Invio e-mail a " + cliente.getEmail());
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 22);
        JLabel oggettoLabel = new JLabel("Oggetto:");
        JTextField oggettoField = new JTextField(20);
        JLabel testoLabel = new JLabel("Testo:");
        JTextArea testoArea = new JTextArea(5,20);

        oggettoLabel.setFont(bodyFont);
        oggettoField.setFont(bodyFont);
        testoLabel.setFont(bodyFont);
        testoArea.setFont(bodyFont);

        JLabel allegatoLabel = new JLabel("File allegato: Nessun allegato");
        JButton sfogliaButton = new JButton("Sfoglia");

        sfogliaButton.addActionListener(new SfogliaListener(this.frame, allegatoLabel));

        allegatoLabel.setFont(bodyFont);
        sfogliaButton.setFont(bodyFont);

        contentPanel.add(oggettoLabel);
        contentPanel.add(oggettoField);
        contentPanel.add(testoLabel);
        contentPanel.add(testoArea);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(allegatoLabel);
        contentPanel.add(sfogliaButton);

        JButton backButton = new JButton("Torna indietro");
        backButton.addActionListener(new GoToMostraClientiListener(this.frame));

        JButton invioButton = new JButton("Invia E-mail");
        invioButton.addActionListener(new MailListener(this.frame, cliente, oggettoField, testoArea));

        southPanel.add(backButton);
        southPanel.add(invioButton);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
