package View;

import Business.ArticoloBusiness;
import Business.Results.ArticoloResult;
import Model.PuntoVendita;
import Model.composite.IProdotto;
import Model.composite.ProdottoComposito;
import View.Listener.GoToDettagliComponenteListener;
import View.Listener.GoToDettagliListener;
import View.Listener.JTableButtonMouseListener;
import View.ViewModel.CatalogoTableModel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraComponentiPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraComponentiPanel(MainFrame frame, ProdottoComposito prodottoComposito, PuntoVendita puntoVendita) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Sottoprodotti di " + prodottoComposito.getName());
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaCatalogo> righe = new ArrayList<>();
        ArticoloResult articoloResult = ArticoloBusiness.getInstance().getType(prodottoComposito);
        ArrayList<IProdotto> prodotti = ((ProdottoComposito) articoloResult.getArticolo()).getSottoprodotti();
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
            dettagliButton.addActionListener(new GoToDettagliComponenteListener(this.frame, p, prodottoComposito, puntoVendita));
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
        tornaIndietroButton.addActionListener(new GoToDettagliListener(this.frame, prodottoComposito, puntoVendita));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
