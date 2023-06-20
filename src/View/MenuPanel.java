package View;

import Business.SessionManager;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;
import Model.Utente;
import View.Decorator.Menu;
import View.Decorator.*;
import View.Listener.GoToLoginListener;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel logoutPanel = new JPanel();

    public MenuPanel(MainFrame frame){
        this.frame = frame;
        this.setLayout(new BorderLayout());

        titlePanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new GridLayout(0,1));

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        JLabel welcomeLabel = new JLabel("Benvenuto: " + u.getUsername());
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        welcomeLabel.setFont(titleFont);
        titlePanel.add(welcomeLabel);

        Menu menu = new GuestMenu(this.frame);

        if(u instanceof Cliente)
            menu = new ClienteMenuDecorator(this.frame);

        else if(u instanceof Manager)
            menu = new ManagerMenuDecorator(this.frame);

        else if(u instanceof Amministratore)
            menu = new AmministratoreMenuDecorator(this.frame);

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        for(JButton btn : menu.getPulsanti()) {
            btn.setFont(bodyFont);
            centerPanel.add(btn);
        }

        JButton logoutButton = new JButton("Logout");
        logoutButton.setActionCommand(GoToLoginListener.LOGOUT);
        logoutButton.addActionListener(new GoToLoginListener(this.frame));
        logoutPanel.add(logoutButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(logoutPanel, BorderLayout.SOUTH);
    }
}
