package View;

import Model.Articolo;
import Model.Feedback;
import Model.composite.IProdotto;
import View.Listener.GoToDettagliListener;
import View.Listener.GoToFeedbackListener;
import org.bouncycastle.asn1.tsp.ArchiveTimeStamp;

import javax.swing.*;
import java.awt.*;

public class CreaFeedbackPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JComboBox gradimentoField;
    private JTextArea commentoField;


    public CreaFeedbackPanel(MainFrame frame, Articolo articolo, String nomePuntoVendita) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Nuovo Feedback");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(6,2));
        JLabel gradimentoLabel = new JLabel("  Gradimento:");
        JLabel commentoLabel = new JLabel("  Commento:");
        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        Font bodyFont1 = new Font(Font.DIALOG, Font.ITALIC, 15);

        gradimentoLabel.setFont(bodyFont);
        commentoLabel.setFont(bodyFont);

        String [] gradimento = {String.valueOf(Feedback.Punteggio.SCARSO), String.valueOf(Feedback.Punteggio.MEDIOCRE),String.valueOf(Feedback.Punteggio.BUONO),String.valueOf(Feedback.Punteggio.OTTIMO),String.valueOf(Feedback.Punteggio.ECCELLENTE) };

        gradimentoField = new JComboBox(gradimento);
        commentoField = new JTextArea();

        gradimentoField.setFont(bodyFont);
        commentoField.setFont(bodyFont1);

        JButton creaFeedback = new JButton("Aggiungi");
        creaFeedback.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToFeedbackListener(this.frame, articolo, nomePuntoVendita));
        tornaIndietroButton.setFont(bodyFont);

        // aggiungere gli action listener

        contentPanel.add(commentoLabel);
        contentPanel.add(commentoField);
        contentPanel.add(gradimentoLabel);
        contentPanel.add(gradimentoField);

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(creaFeedback);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
