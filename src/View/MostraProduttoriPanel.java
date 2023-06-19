package View;

import Business.MagazzinoBusiness;
import Business.ProduttoreBusiness;
import Business.Results.ProduttoreResult;
import Model.Produttore;
import View.Listener.*;
import View.ViewModel.ProduttoreTableModel;
import View.ViewModel.RigaProduttore;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraProduttoriPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraProduttoriPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Produttori ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaProduttore> righe = new ArrayList<>();

        ProduttoreResult result = ProduttoreBusiness.getInstance().caricaProduttori();

        ArrayList<Produttore> produttori = result.getProduttori();
        for(int i = 0 ; i < result.getProduttori().size(); i++){
            RigaProduttore riga = new RigaProduttore();
            Produttore p = produttori.get(i);
            JButton modifica = new JButton("Modifica");
            JButton elimina = new JButton("Elimina");
            riga.setNomeProduttore(p.getNome());
            riga.setEmailProduttore(p.getMail());
            riga.setSitoProduttore(p.getSito());
            riga.setDescrizione(p.getDescrizione());
            riga.setModificaButton(modifica);
            riga.setEliminaButton(elimina);

            modifica.addActionListener(new GoToModificaProduttoreListener(this.frame, p));
            elimina.addActionListener(new RemoveProduttoreListener(this.frame, p));

            righe.add(riga);
        }

        ProduttoreTableModel tableModel = new ProduttoreTableModel(righe);
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

        JButton creaProduttore = new JButton("Crea nuovo produttore");
        creaProduttore.addActionListener(new GoToProduttoreListener(this.frame));

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
