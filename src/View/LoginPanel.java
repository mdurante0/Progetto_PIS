package View;

import View.Listener.LoginListener;

import javax.swing.*;
import java.awt.*;
import View.Listener.LoginListener;

public class LoginPanel extends JFrame {

    private JPanel utenteLoggato = new JPanel();
    private JPanel north = new JPanel();
    private JPanel west = new JPanel();
    private JPanel center = new JPanel();
    private JPanel s = new JPanel();

    private JButton sfogliaCatalogo= new JButton("Sfoglia Catalogo");
    private JButton registrazione = new JButton("Registrati!");
    private JButton login = new JButton("Login");

    public LoginPanel() {
        super("MyShop");

        this.setSize(1080,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        north.setLayout(new FlowLayout());
        center.setBounds(0,0,25,25);
        center.setLayout(new FlowLayout());
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
        center.setBackground(Color.YELLOW);

        north.add(new JLabel("Benvenuto in MyShop!"));
        west.add(sfogliaCatalogo);
        west.add(registrazione);


        // aggiungere action listener per sfogliare il catalogo e la registrazione

        JTextField username = new JTextField(20);
        JPasswordField password = new JPasswordField(20);

        login.setActionCommand(LoginListener.LOGIN_BTN);
        center.add(new JLabel("username"));
        center.add(username);
        center.add(new JLabel("password"));
        center.add(password);
        center.add(login);
        login.setActionCommand(LoginListener.LOGIN_BTN);



        c.add(west, BorderLayout.WEST);
        c.add(north, BorderLayout.NORTH);
        c.add(center, BorderLayout.CENTER);
        this.setVisible(true);
    }

}
