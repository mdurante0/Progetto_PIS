package View;

import Business.CatalogoBusiness;
import Business.Results.CatalogoResult;
import Business.SessionManager;
import Model.Servizio;
import Model.Utente;
import View.Listener.GoToDettagliListener;
import View.Listener.GoToLoginListener;
import View.Listener.GoToMenuListener;
import View.Listener.JTableButtonMouseListener;
import View.ViewModel.CatalogoTableModel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatalogoServiziPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public CatalogoServiziPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Catalogo dei servizi");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaCatalogo> righe = new ArrayList<>();

        CatalogoResult result = CatalogoBusiness.getInstance().caricaCatalogoServizi();
        ArrayList<Servizio> servizi = result.getListaServizi();
        for(int i = 0 ; i < result.getListaServizi().size(); i++){
            RigaCatalogo riga = new RigaCatalogo();
            Servizio servizio = servizi.get(i);
            JButton dettagliButton = new JButton("Dettagli");
            riga.setIdArticolo(servizio.getIdArticolo());
            riga.setNomeProdotto(servizio.getName());
            riga.setNomeRifornitore(servizio.getFornitore().getNome());
            riga.setNomeCategoria(servizio.getCategoria().getNome());
            riga.setPrezzo(servizio.getPrezzo());
            riga.setDettagliButton(dettagliButton);
            dettagliButton.addActionListener(new GoToDettagliListener(this.frame, servizio));
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

        if (u == null)
            tornaIndietroButton.addActionListener(new GoToLoginListener(this.frame));
        else
            tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
