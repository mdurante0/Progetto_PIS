package View;

import Business.ClienteBusiness;
import Business.ListaAcquistoBusiness;
import Business.Results.ClienteResult;
import Business.Results.ListaAcquistoResult;
import Model.Cliente;
import Model.ListaAcquisto;
import View.Listener.GoToMenuListener;
import View.Listener.JTableButtonMouseListener;
import View.ViewModel.ListaAcquistoClienteTableModel;
import View.ViewModel.RigaListaAcquistoCliente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraListeAcquistoClientePanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraListeAcquistoClientePanel(MainFrame frame, Cliente cliente) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Liste di acquisto ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaListaAcquistoCliente> righe = new ArrayList<>();


        ListaAcquistoResult listaAcquistoResult = ListaAcquistoBusiness.getInstance().caricaListeAcquisto(cliente.getUsername());
        ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoResult.getListeAcquisto();
       for(int j = 0 ; j < listeAcquisto.size(); j++) {
            RigaListaAcquistoCliente riga = new RigaListaAcquistoCliente();
            JButton modificaButton = new JButton("Modifica");
            JButton eliminaButton = new JButton("Elimina");

            riga.setNomeLista(listeAcquisto.get(j).getNome());
            if(listeAcquisto.get(j).getPagata()==1)
                riga.setPagata("Pagata");
            else
                riga.setPagata("Non pagata");
            riga.setModificaButton(modificaButton);
            riga.setEliminaButton(eliminaButton);
            j++;
            righe.add(riga);
        }


        //aggiungere action listener


        ListaAcquistoClienteTableModel tableModel = new ListaAcquistoClienteTableModel(righe);
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

        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);

    }
}
