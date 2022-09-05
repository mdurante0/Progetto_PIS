package View;

import Business.SessionManager;
import Model.Cliente;
import Model.Utente;
import View.Decorator.ClienteMenuDecorator;
import View.Decorator.GuestMenu;
import View.Listener.LoginListener;

import javax.swing.*;
import java.awt.*;

public class EsempioGerarchiaLayout extends JFrame {

    private JPanel utenteLoggato = new JPanel();
    private JPanel nord = new JPanel();

    private JPanel west = new JPanel();

    private JPanel centro = new JPanel();

    public EsempioGerarchiaLayout() {
        super("Esempio layout con gerarchia");

        this.setSize(1280,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        JPanel centro = new JPanel();
        JPanel sud = new JPanel();

        nord.setLayout(new FlowLayout());
        centro.setLayout(new GridLayout(2,1));
        sud.setLayout(new FlowLayout());

        JTextField username = new JTextField(20);
        JPasswordField password = new JPasswordField(20);
        JButton login = new JButton("Login");
        login.setActionCommand(LoginListener.LOGIN_BTN);
        nord.add(username);
        nord.add(password);
        nord.add(login);

        /*
        login.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //System.out.println("Cliccato");
                                        String user = username.getText();
                                        String pwd = new String(password.getPassword());
                                        System.out.println("username: "+user);
                                        System.out.println("password: "+pwd);
                                        System.out.println("------------");
                                    }
                                });*/

        LoginListener loginListener = new LoginListener(username, password);
        LoginListener loginListener2 = new LoginListener(this);
        loginListener.setFrame(this);
        login.addActionListener(loginListener);
        //login.addActionListener(loginListener);

        centro.add(new JLabel("Benvenuto, effettua il login"));

        sud.add(new JLabel("Progetto realizzato per l'esame di PIS"));


        c.add(nord, BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        c.add(sud, BorderLayout.SOUTH);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);

        JMenuItem apri = new JMenuItem("Apri...");
        apri.addActionListener(loginListener);
        apri.setActionCommand(LoginListener.APRIFILE_MENU);
        menuFile.add(apri);

        JMenu menuRecenti = new JMenu("Recenti");
        menuFile.add(menuRecenti);
        menuRecenti.add(new JMenuItem("/file1"));
        menuRecenti.add(new JMenuItem("/file2"));
        menuRecenti.add(new JMenuItem("/file3"));

        utenteLoggato.setLayout(new FlowLayout());

        west.setLayout(new GridLayout(10,1));

        View.Decorator.Menu guestMenu = new GuestMenu();

        for(JButton btn : guestMenu.getPulsanti()) {
            west.add(btn);
        }

        getContentPane().add(west, BorderLayout.WEST);


        this.setVisible(true);
    }

    public void mostraPannelloUtenteLoggato(String message) {

        //1. togliere il pannello utente non loggato
        remove(nord);

        //2. inserire il pannello utente loggato
        utenteLoggato.removeAll();
        utenteLoggato.add(new JLabel(message));
        utenteLoggato.add(new JButton("Logout"));
        add(utenteLoggato, BorderLayout.NORTH);

        repaint();
        validate();

    }

    public void aggiornaMenuPulsanti() {
        west.removeAll();

        //..
        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        if(u instanceof Cliente) {
            //decoriamo il menu usando il ClienteMenuDecorator
            View.Decorator.Menu guestMenu = new GuestMenu();
            View.Decorator.Menu clienteMenu = new ClienteMenuDecorator(guestMenu);
            for(JButton btn : clienteMenu.getPulsanti())
                west.add(btn);
        }
        //else if u instanceof Manager....
        //catena di decorator

        repaint();
        validate();
    }

    public void mostraCatalogo() {
        centro.removeAll();
        centro.add(new CatalogoPanel());
        repaint();
        validate();
    }
}
