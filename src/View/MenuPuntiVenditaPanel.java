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
        centerPanel.setLayout(new FlowLayout());
        goBackPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Scegli il punto vendita!");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        PuntoVenditaResult result = PuntoVenditaBusiness.getInstance().caricaPuntiVendita();
        if(!result.getPuntiVendita().isEmpty()) {
            centerPanel.setLayout(new GridLayout(result.getPuntiVendita().size(), 1));
            for (PuntoVendita puntovendita : result.getPuntiVendita()) {
                JButton puntoVenditaButton = new JButton(puntovendita.getNome());
                puntoVenditaButton.addActionListener(new GoToCatalogoListener(this.frame, puntovendita.getNome()));

                JLabel puntoVenditaLabel = new JLabel("  Punto vendita in " + puntovendita.getIndirizzo() + ":");
                puntoVenditaLabel.setFont(bodyFont);
                puntoVenditaButton.setFont(bodyFont);

                centerPanel.add(puntoVenditaLabel);
                centerPanel.add(puntoVenditaButton);
            }
        }

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        JButton tornaIndietroButton = new JButton("Torna indietro");
        if (u == null)
            tornaIndietroButton.addActionListener(new GoToLoginListener(this.frame));
        else
            tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));
        goBackPanel.add(tornaIndietroButton);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(goBackPanel, BorderLayout.SOUTH);
    }
}
