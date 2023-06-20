package View;

import Business.ClienteBusiness;
import Business.PrenotazioneBusiness;
import Business.PuntoVenditaBusiness;
import Business.Results.ClienteResult;
import Business.Results.PrenotazioneResult;
import Business.Results.PuntoVenditaResult;
import Business.SessionManager;
import Model.*;
import View.Listener.GoToMenuListener;
import View.Listener.JTableButtonMouseListener;
import View.Listener.RemovePrenotazioneListener;
import View.ViewModel.PrenotazioneTableModel;
import View.ViewModel.RigaPrenotazione;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraPrenotazioniPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraPrenotazioniPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Prenotazioni ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaPrenotazione> righe = new ArrayList<>();

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        ArrayList<Cliente> clienti = new ArrayList<>();
        ClienteResult clienteResult = new ClienteResult();
        if(u instanceof Manager m) {
            PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByManager(m);
            clienteResult = ClienteBusiness.getInstance().caricaClienteByPuntoVendita(puntoVenditaResult.getPuntiVendita().get(0));
            clienti = clienteResult.getClienti();
        }
        else if (u instanceof Amministratore) {
            clienteResult = ClienteBusiness.getInstance().caricaClienti();
            clienti = clienteResult.getClienti();
        }
        else if(u instanceof Cliente c) {
            clienti.add(c);
        }
        for(int i = 0 ; i < clienti.size(); i++){

            Cliente c = clienti.get(i);
            PrenotazioneResult prenotazioneResult = PrenotazioneBusiness.getInstance().caricaPrenotazioniByUser(c.getUsername());
            if (!prenotazioneResult.getPrenotazioni().isEmpty()){
                Prenotazione p = prenotazioneResult.getPrenotazioni().get(i);
                for (int j = 0; j < p.getProdotti().size(); j++) {
                    RigaPrenotazione riga = new RigaPrenotazione();
                    JButton elimina = new JButton("Elimina");
                    riga.setUsernameCliente(c.getUsername());
                    riga.setNomeProdotto(p.getProdotti().get(j).getName());
                    riga.setQuantitaProdotto(p.getProdotti().get(j).getQuantita());
                    riga.setData(p.getDataPrenotazione());
                    riga.setEliminaButton(elimina);
                    elimina.addActionListener(new RemovePrenotazioneListener(this.frame, p, p.getProdotti().get(j)));
                    righe.add(riga);
                }
            }

        }

        PrenotazioneTableModel tableModel = new PrenotazioneTableModel(righe);
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
