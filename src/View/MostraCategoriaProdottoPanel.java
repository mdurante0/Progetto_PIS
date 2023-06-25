package View;

import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaProdotto;
import View.Listener.*;
import View.ViewModel.CategoriaTableModel;
import View.ViewModel.RigaCategoriaProdotto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraCategoriaProdottoPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraCategoriaProdottoPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Categorie Prodotti ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaCategoriaProdotto> righe = new ArrayList<>();

        CategoriaResult result = CategoriaBusiness.getInstance().caricaCategorieProdotto();

        ArrayList<CategoriaProdotto> categorieProdotti = result.getCategorieProdotto();

        for(int i = 0 ; i < categorieProdotti.size(); i++){
            CategoriaProdotto f = categorieProdotti.get(i);
            RigaCategoriaProdotto riga = new RigaCategoriaProdotto();
            JButton modifica = new JButton("Modifica");
            JButton elimina = new JButton("Elimina");
            riga.setNomeCategoria(f.getNome());
            if (categorieProdotti.get(i).getIdCategoriaProdottoParent() != 0){
                result = CategoriaBusiness.getInstance().caricaCategoriaProdottoById(f.getIdCategoriaProdottoParent());
                CategoriaProdotto c = result.getCategorieProdotto().get(0);
                riga.setNomeSopraCategoria(c.getNome());

            }

            riga.setModificaButton(modifica);
            riga.setEliminaButton(elimina);

            modifica.addActionListener(new GoToModificaCategoriaProdottoListener(this.frame, f));
            elimina.addActionListener(new RemoveCategoriaProdottoListener(this.frame , f));

            righe.add(riga);
        }

        CategoriaTableModel tableModel = new CategoriaTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
        JTableButtonMouseListener mouseListener = new JTableButtonMouseListener(tabella);
        tabella.getColumn("Modifica").setCellRenderer(buttonRenderer);
        tabella.getColumn("Elimina").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(mouseListener);

        contentPanel.add(new JLabel("          "), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(new JLabel("          "), BorderLayout.EAST);

        JButton creaCategoria = new JButton("Crea Categoria");
        creaCategoria.addActionListener(new GoToCreaCategoriaProdottoListener(this.frame));

        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);
        southPanel.add(creaCategoria);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
