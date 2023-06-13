package View;

import View.Decorator.GuestMenu;
import View.Listener.GoToRegistrationListener;
import View.Listener.LoginListener;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel usernamePanel = new JPanel();
    private JPanel passwordPanel = new JPanel();
    private JPanel fieldsPanel = new JPanel();
    private JPanel loginButtonPanel = new JPanel();
    private JPanel center = new JPanel();
    private JPanel guestPanel = new JPanel();
    private JButton sfogliaCatalogoButton = new JButton("Sfoglia Catalogo");
    private JButton registrazioneButton = new JButton("Registrati");
    private JButton loginButton = new JButton("Login");

    public LoginPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());
        titlePanel.setLayout(new FlowLayout());
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        fieldsPanel.setLayout(new GridLayout(16,1));
        usernamePanel.setLayout(new FlowLayout());
        passwordPanel.setLayout(new FlowLayout());
        guestPanel.setLayout(new FlowLayout());

        JLabel myShopLabel = new JLabel("Benvenuto in MyShop!");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        myShopLabel.setFont(titleFont);
        titlePanel.add(myShopLabel);

        JTextField usernameField = new JTextField(20);
        JLabel usernameLabel = new JLabel("Username: ");
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPasswordField passwordField = new JPasswordField(20);
        JLabel passwordLabel = new JLabel("Password: ");
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        LoginListener loginListener = new LoginListener(usernameField, passwordField, this.frame);
        loginButton.addActionListener(loginListener);
        loginButtonPanel.add(loginButton);

        View.Decorator.Menu guestMenu = new GuestMenu(this.frame);
        for(JButton btn : guestMenu.getPulsanti()) {
            guestPanel.add(btn);
        }

        guestPanel.add(new JLabel(" "));

        registrazioneButton.addActionListener(new GoToRegistrationListener(this.frame));
        guestPanel.add(registrazioneButton);

        fieldsPanel.add(usernamePanel);
        fieldsPanel.add(passwordPanel);
        fieldsPanel.add(loginButtonPanel);
        fieldsPanel.add(new JLabel(""));
        fieldsPanel.add(guestPanel);

        center.add(fieldsPanel);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(center, BorderLayout.CENTER);
    }

}
