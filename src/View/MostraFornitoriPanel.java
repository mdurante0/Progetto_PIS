package View;

import Business.FornitoreBusiness;
import Business.ProduttoreBusiness;
import Business.Results.FornitoreResult;
import Model.Fornitore;
import View.Listener.*;
import View.ViewModel.FornitoreTableModel;
import View.ViewModel.RigaFornitore;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraFornitoriPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraFornitoriPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Fornitori ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaFornitore> righe = new ArrayList<>();

        FornitoreResult result = FornitoreBusiness.getInstance().caricaFornitori();

        ArrayList<Fornitore> produttori = result.getFornitori();
        for(int i = 0 ; i < result.getFornitori().size(); i++){
            RigaFornitore riga = new RigaFornitore();
            Fornitore f = produttori.get(i);
            JButton modifica = new JButton("Modifica");
            JButton elimina = new JButton("Elimina");
            riga.setNomeFornitore(f.getNome());
            riga.setEmailFornitore(f.getMail());
            riga.setSitoFornitore(f.getSito());
            riga.setDescrizione(f.getDescrizione());
            riga.setModificaButton(modifica);
            riga.setEliminaButton(elimina);

            modifica.addActionListener(new GoToModificaFornitoreListener(this.frame, f));
            elimina.addActionListener(new RemoveFornitoreListener(this.frame, f));

            righe.add(riga);
        }

        FornitoreTableModel tableModel = new FornitoreTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
        tabella.getColumn("Modifica").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(new JTableButtonMouseListener(tabella));

        tabella.getColumn("Elimina").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(new JTableButtonMouseListener(tabella));

        contentPanel.add(new JLabel("          "), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(new JLabel("          "), BorderLayout.EAST);

        JButton creaProduttore = new JButton("Crea nuovo fornitore");
        creaProduttore.addActionListener(new GoToFornitoreListener(this.frame));

        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);
        southPanel.add(creaProduttore);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
