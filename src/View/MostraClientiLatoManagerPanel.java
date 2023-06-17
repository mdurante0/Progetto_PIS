package View;

import Business.ClienteBusiness;
import Business.Results.ClienteResult;
import Model.Cliente;
import Model.PuntoVendita;
import View.Listener.GoToMenuListener;
import View.Listener.JTableButtonMouseListener;
import View.ViewModel.ClienteTableModel;
import View.ViewModel.RigaCliente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraClientiLatoManagerPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraClientiLatoManagerPanel(MainFrame frame, PuntoVendita puntoVendita) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Clienti ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());
        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);

        ArrayList<RigaCliente> righe = new ArrayList<>();


        ClienteResult clienteResult = ClienteBusiness.getInstance().caricaClienteByPuntoVendita(puntoVendita);
        ArrayList<Cliente> clienti = clienteResult.getClienti();

        for(int i = 0 ; i < clienteResult.getClienti().size(); i++){

            RigaCliente rigaCliente = new RigaCliente();

            JButton eliminaButton = new JButton("Elimina");
            JButton abilitazioneButton = new JButton("Abilitato");

            Cliente c = clienti.get(i);


            rigaCliente.setUsername(c.getUsername());
            rigaCliente.setEmail(c.getEmail());
            rigaCliente.setNomeCliente(c.getName());
            rigaCliente.setCongnomeCliente(c.getSurname());
            rigaCliente.setAbilitazione(abilitazioneButton);
            rigaCliente.setEliminaButton(eliminaButton);

            righe.add(rigaCliente);
            //aggiungere action listener

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
        tabella.addMouseListener(new JTableButtonMouseListener(tabella));

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
