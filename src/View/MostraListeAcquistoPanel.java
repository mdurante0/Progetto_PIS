package View;

import Business.*;
import Business.Results.ClienteResult;
import Business.Results.ListaAcquistoResult;
import Business.Results.PuntoVenditaResult;
import Model.*;
import View.Listener.GoToMenuListener;
import View.Listener.JTableButtonMouseListener;
import View.ViewModel.ListaAcquistoTableModel;
import View.ViewModel.RigaListaAcquisto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraListeAcquistoPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraListeAcquistoPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Liste di acquisto ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaListaAcquisto> righe = new ArrayList<>();

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        if (u instanceof Cliente c){
            ListaAcquistoResult listaAcquistoResult = ListaAcquistoBusiness.getInstance().caricaListeAcquisto(c.getUsername());
            ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoResult.getListeAcquisto();
            for(int i = 0 ; i < listeAcquisto.size(); i++){
                    RigaListaAcquisto riga = new RigaListaAcquisto();
                    JButton DettagliButton = new JButton("Dettagli");
                    JButton eliminaButton = new JButton("Elimina");
                    riga.setPagata(String.valueOf(listeAcquisto.get(i).getPagata()));
                    riga.setNomeLista(listeAcquisto.get(i).getNome());
                    riga.setDettagliButton(DettagliButton);
                    riga.setEliminaButton(eliminaButton);

                    righe.add(riga);

                //aggiungere action listener

            }

        }
        if (u instanceof Manager m){
            PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByManager(m);
            PuntoVendita puntoVendita = puntoVenditaResult.getPuntiVendita().get(0);

            ClienteResult clienteResult = ClienteBusiness.getInstance().caricaClienteByPuntoVendita(puntoVendita);

            ArrayList<Cliente> clienti = clienteResult.getClienti();

            for(int i = 0 ; i < clienti.size(); i++){
                ListaAcquistoResult listaAcquistoResult = ListaAcquistoBusiness.getInstance().caricaListeAcquisto(clienti.get(i).getUsername());

                ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoResult.getListeAcquisto();

                for (int j = 0; j < listeAcquisto.size(); j++) {

                    RigaListaAcquisto riga = new RigaListaAcquisto();
                    JButton eliminaButton = new JButton("Elimina");
                    JButton pagataButton = new JButton("Pagata");
                    riga.setUsernameCliente(clienti.get(i).getUsername());
                    riga.setNomeLista(listeAcquisto.get(j).getNome());
                    riga.setPagata(pagataButton);
                    riga.setEliminaButton(eliminaButton);
                    j++;
                    righe.add(riga);
                }
                //aggiungere action listener

            }

        }
        if (u instanceof Amministratore){
            ClienteResult clienteResult = ClienteBusiness.getInstance().caricaClienti();

            ArrayList<Cliente> clienti = clienteResult.getClienti();

            for(int i = 0 ; i < clienti.size(); i++){
                ListaAcquistoResult listaAcquistoResult = ListaAcquistoBusiness.getInstance().caricaListeAcquisto(clienti.get(i).getUsername());

                ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoResult.getListeAcquisto();

                for (int j = 0; j < listeAcquisto.size(); j++) {

                    RigaListaAcquisto riga = new RigaListaAcquisto();
                    JButton eliminaButton = new JButton("Elimina");
                    JButton pagataButton = new JButton("Pagata");
                    riga.setUsernameCliente(clienti.get(i).getUsername());
                    riga.setNomeLista(listeAcquisto.get(j).getNome());
                    riga.setPagata(pagataButton);
                    riga.setEliminaButton(eliminaButton);
                    j++;
                    righe.add(riga);
                }
                //aggiungere action listener

            }
        }

        ListaAcquistoTableModel tableModel = new ListaAcquistoTableModel(righe, u);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
        JTableButtonMouseListener mouseListener = new JTableButtonMouseListener(tabella);
        if (u instanceof Cliente)
             tabella.getColumn("Dettagli").setCellRenderer(buttonRenderer);
        else
            tabella.getColumn("Stato pagamento").setCellRenderer(buttonRenderer);

        tabella.getColumn("Elimina").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(mouseListener);

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
