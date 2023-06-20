package View;

import Business.SessionManager;
import Model.Amministratore;
import Model.Articolo;
import Model.PuntoVendita;
import Model.Utente;
import Model.composite.IProdotto;
import View.Listener.GoToDettagliListener;
import View.Listener.GoToMenuListener;
import View.Listener.GoToMenuPuntiVenditaListener;
import View.Listener.JTableButtonMouseListener;
import View.ViewModel.CatalogoTableModel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatalogoProdottiPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public CatalogoProdottiPanel(MainFrame frame, PuntoVendita puntoVendita) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Catalogo di " + puntoVendita.getNome());
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaCatalogo> righe = new ArrayList<>();
        ArrayList<IProdotto> prodotti = puntoVendita.getMagazzino().getProdotti();
        for(int i = 0 ; i < prodotti.size(); i++){
            RigaCatalogo riga = new RigaCatalogo();
            IProdotto p = prodotti.get(i);
            JButton dettagliButton = new JButton("Dettagli");
            riga.setIdArticolo(p.getIdArticolo());
            riga.setNome(p.getName());
            riga.setNomeRifornitore(p.getProduttore().getNome());
            riga.setNomeCategoria(p.getCategoria().getNome());
            riga.setPrezzo(p.getPrezzo());
            riga.setDettagliButton(dettagliButton);
            dettagliButton.addActionListener(new GoToDettagliListener(this.frame, (Articolo) p, puntoVendita));
            righe.add(riga);
        }

        CatalogoTableModel tableModel = new CatalogoTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
        tabella.getColumn("Dettagli").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(new JTableButtonMouseListener(tabella));

        contentPanel.add(new JLabel("          "), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(new JLabel("          "), BorderLayout.EAST);

        JButton tornaIndietroButton = new JButton("Torna indietro");
        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        if(u == null || u instanceof Amministratore)
            tornaIndietroButton.addActionListener(new GoToMenuPuntiVenditaListener(this.frame));
        else
            tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
