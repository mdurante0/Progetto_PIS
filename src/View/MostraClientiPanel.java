package View;

import Business.ClienteBusiness;
import Business.PuntoVenditaBusiness;
import Business.Results.ClienteResult;
import Business.Results.PuntoVenditaResult;
import Business.SessionManager;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;
import Model.Utente;
import View.Listener.*;
import View.ViewModel.ClienteTableModel;
import View.ViewModel.RigaCliente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraClientiPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraClientiPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Clienti ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaCliente> righe = new ArrayList<>();

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        ClienteResult clienteResult;
        ArrayList<Cliente> clienti = new ArrayList<>();
        if (u instanceof Amministratore) {
            clienteResult = ClienteBusiness.getInstance().caricaClienti();
            clienti = clienteResult.getClienti();
        }
        else if (u instanceof Manager m) {
            PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByManager(m);
            clienteResult = ClienteBusiness.getInstance().caricaClienteByPuntoVendita(puntoVenditaResult.getPuntiVendita().get(0));
            clienti = clienteResult.getClienti();
        }

        for (int i = 0; i < clienti.size(); i++) {
            RigaCliente rigaCliente = new RigaCliente();
            JButton eliminaButton = new JButton("Elimina");
            JButton abilitazioneButton = new JButton("Abilitato");
            JButton emailButton = new JButton("Invia E-mail");

            Cliente c = clienti.get(i);
            if (!c.isAbilitazione())
                abilitazioneButton.setText("Disabilitato");

            rigaCliente.setUsername(c.getUsername());
            rigaCliente.setEmail(c.getEmail());
            rigaCliente.setNomeCliente(c.getName());
            rigaCliente.setCongnomeCliente(c.getSurname());
            rigaCliente.setEmailButton(emailButton);
            rigaCliente.setAbilitazioneButton(abilitazioneButton);
            rigaCliente.setEliminaButton(eliminaButton);

            emailButton.addActionListener(new GoToMailListener(this.frame, c));
            eliminaButton.addActionListener(new RemoveClienteListener(this.frame, c));
            abilitazioneButton.addActionListener(new AbilitazioneClienteListener(this.frame, c, abilitazioneButton));

            righe.add(rigaCliente);
        }

        ClienteTableModel tableModel = new ClienteTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
        tabella.getColumn("Elimina").setCellRenderer(buttonRenderer);
        tabella.getColumn("Invio E-mail").setCellRenderer(buttonRenderer);
        tabella.getColumn("Abilitazione").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(new JTableButtonMouseListener(tabella));

        contentPanel.add(new JLabel("          "), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(new JLabel("          "), BorderLayout.EAST);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));
        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}

