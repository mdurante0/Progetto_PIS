package View;

import Business.PuntoVenditaBusiness;
import Business.Results.PuntoVenditaResult;
import Business.SessionManager;
import Model.PuntoVendita;
import Model.Utente;
import View.Listener.GoToCatalogoListener;
import View.Listener.GoToLoginListener;
import View.Listener.GoToMenuListener;

import javax.swing.*;
import java.awt.*;

public class MenuPuntiVenditaPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel goBackPanel = new JPanel();
    public MenuPuntiVenditaPanel(MainFrame frame){
        this.frame = frame;

        this.setLayout(new BorderLayout());
        titlePanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new GridLayout(0, 2));
        goBackPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Scegli il punto vendita!");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 50, 300);
        this.add(scrollPane);

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        PuntoVenditaResult result = PuntoVenditaBusiness.getInstance().caricaPuntiVendita();
        if(!result.getPuntiVendita().isEmpty()) {

            for (PuntoVendita puntovendita : result.getPuntiVendita()) {
                JButton puntoVenditaButton = new JButton(puntovendita.getNome());
                puntoVenditaButton.setActionCommand(GoToCatalogoListener.PRODOTTI);
                puntoVenditaButton.addActionListener(new GoToCatalogoListener(this.frame, puntovendita));

                JLabel puntoVenditaLabel = new JLabel("  Punto vendita in " + puntovendita.getIndirizzo() + ":");
                puntoVenditaLabel.setFont(bodyFont);
                puntoVenditaButton.setFont(bodyFont);

                centerPanel.add(puntoVenditaLabel);
                centerPanel.add(puntoVenditaButton);
            }
        }

        JLabel tuttiIProdottiLabel = new JLabel("  Visualizza tutti i prodotti:");
        tuttiIProdottiLabel.setFont(bodyFont);

        JButton tuttiIProdottiButton = new JButton("Tutti i prodotti");
        tuttiIProdottiButton.setFont(bodyFont);
        tuttiIProdottiButton.setActionCommand(GoToCatalogoListener.PRODOTTI);
        tuttiIProdottiButton.addActionListener(new GoToCatalogoListener(this.frame));
        centerPanel.add(tuttiIProdottiLabel);
        centerPanel.add(tuttiIProdottiButton);

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        JButton tornaIndietroButton = new JButton("Torna indietro");
        if (u == null)
            tornaIndietroButton.addActionListener(new GoToLoginListener(this.frame));
        else
            tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));
        goBackPanel.add(tornaIndietroButton);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(goBackPanel, BorderLayout.SOUTH);
    }
}
