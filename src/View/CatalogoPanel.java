package View;

import DAO.*;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        this.setLayout(new BorderLayout());

        java.util.List<RigaCatalogo> righe = new ArrayList<RigaCatalogo>();
        //popoliamo con dati fake (voi dovete popolare tramite DAO)

        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        prodotti = prodottoDAO.findAll();

        for(int i =0 ; i < prodotti.size(); i++){
            RigaCatalogo riga = new RigaCatalogo();
            Prodotto p = prodotti.get(i);
            int idProduttore = p.getProduttore().getIdProduttore();
            int idCategoria = p.getCategoria().getIdCategoria();

            riga.setIdProdotto(p.getIdArticolo());
            riga.setNomeProdotto(p.getName());
            riga.setNomeProduttore(produttoreDAO.findById(idProduttore).getNome());
            riga.setNomeCategoria(categoriaProdottoDAO.findById(idCategoria).getNome());
            riga.setPrezzo(p.getPrezzo());
            righe.add(riga);
        }


        CatalogoTableModel tableModel = new CatalogoTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(200);
        tabella.setAutoscrolls(true);

        JScrollPane scrollPane = new JScrollPane(tabella);
        add(scrollPane, BorderLayout.CENTER);

        JPanel pulsantiAzioneTabella = new JPanel();
        pulsantiAzioneTabella.setLayout(new FlowLayout());
        JButton mettiNelCarrello = new JButton("Metti nel carrello");

        contentPanel.add(tabella);


        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(mettiNelCarrello, BorderLayout.SOUTH);

        setVisible(true);

    }
}
