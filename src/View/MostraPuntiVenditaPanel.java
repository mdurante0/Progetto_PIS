package View;

import Business.PuntoVenditaBusiness;
import Business.Results.PuntoVenditaResult;
import Model.PuntoVendita;
import View.Listener.*;
import View.ViewModel.PuntoVenditaTableModel;
import View.ViewModel.RigaPuntoVendita;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraPuntiVenditaPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraPuntiVenditaPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Punti Vendita ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaPuntoVendita> righe = new ArrayList<>();

        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntiVendita();

        ArrayList<PuntoVendita> puntiVendita = puntoVenditaResult.getPuntiVendita();

        for (int i = 0; i < puntiVendita.size(); i++) {
            RigaPuntoVendita rigaPuntoVendita = new RigaPuntoVendita();
            JButton modificaButton = new JButton("Modifica");
            JButton eliminaButton = new JButton("Elimina");

            rigaPuntoVendita.setNomePuntoVendita(puntiVendita.get(i).getNome());
            rigaPuntoVendita.setManager(puntiVendita.get(i).getManager().getName());
            rigaPuntoVendita.setMagazzino(puntiVendita.get(i).getMagazzino().getIndirizzo());
            rigaPuntoVendita.setCitta(puntiVendita.get(i).getCitta());
            rigaPuntoVendita.setIndirizzo(puntiVendita.get(i).getIndirizzo());
            rigaPuntoVendita.setTelefono(puntiVendita.get(i).getTelefono());
            rigaPuntoVendita.setModificaButton(modificaButton);
            rigaPuntoVendita.setEliminaButton(eliminaButton);

            modificaButton.addActionListener(new GoToModificaPuntoVenditaListener(this.frame, puntiVendita.get(i)));
            eliminaButton.addActionListener(new RemovePuntoVenditaListener(this.frame,puntiVendita.get(i)));

            righe.add(rigaPuntoVendita);
        }


            PuntoVenditaTableModel tableModel = new PuntoVenditaTableModel(righe);
            JTable tabella = new JTable(tableModel);

            tabella.setRowHeight(100);
            tabella.setAutoscrolls(true);
            tabella.setRowSelectionAllowed(false);
            tabella.setFocusable(false);

            JScrollPane scrollPane = new JScrollPane(tabella);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
            tabella.getColumn("Elimina").setCellRenderer(buttonRenderer);

            tabella.getColumn("Modifica").setCellRenderer(buttonRenderer);
            tabella.addMouseListener(new JTableButtonMouseListener(tabella));

            contentPanel.add(new JLabel("          "), BorderLayout.WEST);
            contentPanel.add(scrollPane, BorderLayout.CENTER);
            contentPanel.add(new JLabel("          "), BorderLayout.EAST);

            JButton tornaIndietroButton = new JButton("Torna indietro");
            tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));

            JButton creaPuntoVenditaButton = new JButton("Crea un nuovo punto vendita");
            creaPuntoVenditaButton.addActionListener(new GoToCreaPuntoVenditaListener(this.frame));

            southPanel.setLayout(new FlowLayout());
            southPanel.add(tornaIndietroButton);
            southPanel.add(creaPuntoVenditaButton);

            this.add(contentPanel, BorderLayout.CENTER);
            this.add(titlePanel, BorderLayout.PAGE_START);
            this.add(southPanel, BorderLayout.SOUTH);
        }
    }

