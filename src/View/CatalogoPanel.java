package View;

import Business.CatalogoBusiness;
import Business.Results.CatalogoResult;
import Model.composite.IProdotto;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatalogoPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    public CatalogoPanel(MainFrame frame) {

        JLabel titleLabel = new JLabel("Il nostro catalogo");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        java.util.List<RigaCatalogo> righe = new ArrayList<>();

        CatalogoResult result = CatalogoBusiness.getInstance().caricaCatalogoProdotti("MyPuntoVendita");
        ArrayList<IProdotto> prodotti = result.getListaProdotti();
        for(int i =0 ; i < result.getListaProdotti().size(); i++){
            RigaCatalogo riga = new RigaCatalogo();
            IProdotto p = prodotti.get(i);

            riga.setIdProdotto(p.getIdArticolo());
            riga.setNomeProdotto(p.getName());
            riga.setNomeProduttore(p.getProduttore().getNome());
            riga.setNomeCategoria(p.getCategoria().getNome());
            riga.setPrezzo(p.getPrezzo());
            righe.add(riga);
        }

        //righe vuote per test
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());
        righe.add(new RigaCatalogo());

        CatalogoTableModel tableModel = new CatalogoTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        contentPanel.add(new JLabel("          "), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(new JLabel("          "), BorderLayout.EAST);

        JPanel pulsantiAzioneTabella = new JPanel();
        pulsantiAzioneTabella.setLayout(new FlowLayout());
        JButton mettiNelCarrello = new JButton("Torna indietro");

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(mettiNelCarrello, BorderLayout.SOUTH);

        setVisible(true);

    }
}
