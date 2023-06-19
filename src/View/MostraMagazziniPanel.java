package View;

import Business.MagazzinoBusiness;
import Business.Results.MagazzinoResult;
import Model.Magazzino;
import View.Listener.*;
import View.ViewModel.MagazzinoTableModel;
import View.ViewModel.RigaMagazzino;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraMagazziniPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraMagazziniPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Magazzini ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaMagazzino> righe = new ArrayList<>();

        MagazzinoResult result = MagazzinoBusiness.getInstance().caricaMagazzini();

        ArrayList<Magazzino> magazzini = result.getMagazzini();
        for(int i = 0 ; i < result.getMagazzini().size(); i++){
            RigaMagazzino riga = new RigaMagazzino();
            Magazzino m = magazzini.get(i);
            JButton modifica = new JButton("Modifica");
            JButton elimina = new JButton("Elimina");
            riga.setQuantitaCorsie(m.getQuantitaCorsie());
            riga.setQuantitaScaffali(m.getQuantitaScaffali());
            riga.setIndirizzo(m.getIndirizzo());
            riga.setModificaButton(modifica);
            riga.setEliminaButton(elimina);

            modifica.addActionListener(new GoToModificaMagazzinoListener(this.frame, m));
            elimina.addActionListener(new RemoveMagazzinoListener(this.frame, m));

            righe.add(riga);
        }

        MagazzinoTableModel tableModel = new MagazzinoTableModel(righe);
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

        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));

        JButton creaMagazzinoButton = new JButton("Crea nuovo magazzino");
        creaMagazzinoButton.addActionListener(new GoToCreaMagazzinoListener(this.frame));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);
        southPanel.add(creaMagazzinoButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
