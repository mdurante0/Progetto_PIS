package View;

import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaServizio;
import View.Listener.*;
import View.ViewModel.CategoriaServizioTableModel;
import View.ViewModel.RigaCategoria;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraCategoriaServizioPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraCategoriaServizioPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Categorie Servizio");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaCategoria> righe = new ArrayList<>();

        CategoriaResult result = CategoriaBusiness.getInstance().caricaCategorieServizio();
        ArrayList<CategoriaServizio> categorieServizio = result.getCategorieServizio();

        for(int i = 0 ; i < categorieServizio.size(); i++){
            CategoriaServizio categoriaServizio = categorieServizio.get(i);
            RigaCategoria riga = new RigaCategoria();
            JButton modifica = new JButton("Modifica");
            JButton elimina = new JButton("Elimina");
            riga.setNomeCategoria(categoriaServizio.getNome());
            riga.setModificaButton(modifica);
            riga.setEliminaButton(elimina);

            modifica.addActionListener(new GoToModificaCategoriaListener(this.frame, categoriaServizio));
            elimina.addActionListener(new RemoveCategoriaProdottoListener(this.frame , categoriaServizio));

            righe.add(riga);
        }

        CategoriaServizioTableModel tableModel = new CategoriaServizioTableModel(righe);
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
        creaCategoria.addActionListener(new GoToCreaCategoriaServizioListener(this.frame));

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
