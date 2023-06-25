package View;

import Business.ListaAcquistoBusiness;
import Business.Results.ListaAcquistoResult;
import Business.SessionManager;
import Model.*;
import View.Listener.*;
import View.ViewModel.AggiungiAListaAcquistoTableModel;
import View.ViewModel.RigaListaAcquisto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AggiungiAListaAcquistoPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public AggiungiAListaAcquistoPanel(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Liste di acquisto");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaListaAcquisto> righe = new ArrayList<>();
        Cliente c = (Cliente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        ListaAcquistoResult listaAcquistoResult = ListaAcquistoBusiness.getInstance().caricaListeAcquistoByCliente(c.getUsername());
        ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoResult.getListeAcquisto();
        for(int i = 0 ; i < listeAcquisto.size(); i++){
            RigaListaAcquisto riga = new RigaListaAcquisto();
            JButton aggiungiButton = new JButton("Aggiungi");
            riga.setNomeLista(listeAcquisto.get(i).getNome());
            riga.setAggiungiButton(aggiungiButton);
            riga.setCostoTotale(listeAcquisto.get(i).getCostoFinale());
            aggiungiButton.addActionListener(new AggiungiAListaListener(this.frame, listeAcquisto.get(i), articolo, puntoVendita));
            righe.add(riga);
        }

        AggiungiAListaAcquistoTableModel tableModel = new AggiungiAListaAcquistoTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
        JTableButtonMouseListener mouseListener = new JTableButtonMouseListener(tabella);
        tabella.getColumn("Aggiungi").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(mouseListener);

        contentPanel.add(new JLabel("          "), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(new JLabel("          "), BorderLayout.EAST);

        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToDettagliListener(this.frame, articolo, puntoVendita));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
