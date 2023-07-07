package View;

import Business.ListaAcquistoBusiness;
import Business.PuntoVenditaBusiness;
import Business.Results.ListaAcquistoResult;
import Business.Results.PuntoVenditaResult;
import Business.SessionManager;
import Model.*;
import View.Listener.*;
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
        JLabel titleLabel = new JLabel("Liste di acquisto");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaListaAcquisto> righe = new ArrayList<>();

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        ListaAcquistoResult listaAcquistoResult;
        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();

        if (u instanceof Cliente c){
            listaAcquistoResult = ListaAcquistoBusiness.getInstance().caricaListeAcquistoByCliente(c.getUsername());
            listeAcquisto = listaAcquistoResult.getListeAcquisto();

        }
        else if (u instanceof Manager m){
            PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByManager(m);
            PuntoVendita puntoVendita = puntoVenditaResult.getPuntiVendita().get(0);

            listaAcquistoResult = ListaAcquistoBusiness.getInstance().caricaListeAcquistoByPuntoVendita(puntoVendita);
            listeAcquisto = listaAcquistoResult.getListeAcquisto();
        }
        else if (u instanceof Amministratore){
            listaAcquistoResult = ListaAcquistoBusiness.getInstance().caricaListeAcquisto();
            listeAcquisto = listaAcquistoResult.getListeAcquisto();
        }

        for (int i = 0; i < listeAcquisto.size(); i++) {
            JButton dettagliButton = new JButton("Dettagli");
            JButton eliminaButton = new JButton("Elimina");
            JButton pagataButton = new JButton();
            RigaListaAcquisto riga = new RigaListaAcquisto();

            if (listeAcquisto.get(i).isPagata()) {
                pagataButton.setText("Acquistata");
                riga.setPagata("Acquistata");
            }
            else {
                pagataButton.setText("Non Acquistata");
                riga.setPagata("Non Acquistata");
                riga.setEliminaButton(eliminaButton);
            }
            riga.setUsernameCliente(listeAcquisto.get(i).getCliente().getUsername());
            riga.setNomeLista(listeAcquisto.get(i).getNome());
            if (u instanceof Manager || u instanceof Amministratore)
                riga.setPagata(pagataButton);
            riga.setDettagliButton(dettagliButton);
            riga.setEliminaButton(eliminaButton);
            riga.setCostoTotale(listeAcquisto.get(i).getCostoFinale());
            eliminaButton.addActionListener(new RemoveListaAcquistoListener(this.frame, listeAcquisto.get(i)));
            dettagliButton.addActionListener(new GoToDettagliListaAcquistoListener(this.frame, listeAcquisto.get(i)));
            pagataButton.addActionListener(new SetPagataListener(this.frame, listeAcquisto.get(i), pagataButton));
            righe.add(riga);
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
