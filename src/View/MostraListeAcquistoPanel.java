package View;

import Business.ClienteBusiness;
import Business.ListaAcquistoBusiness;
import Business.PrenotazioneBusiness;
import Business.Results.ClienteResult;
import Business.Results.ListaAcquistoResult;
import Model.Cliente;
import Model.ListaAcquisto;
import View.Listener.GoToMenuListener;
import View.Listener.JTableButtonMouseListener;
import View.ViewModel.ListaAcquistoTableModel;
import View.ViewModel.RigaListaAcquisto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraListeAcquistoPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraListeAcquistoPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Liste di acquisto ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaListaAcquisto> righe = new ArrayList<>();


        ClienteResult clienteResult = ClienteBusiness.getInstance().caricaClienti();
        ArrayList<Cliente> clienti = clienteResult.getClienti();

        for(int i = 0 ; i < clienteResult.getClienti().size(); i++){

            Cliente c = clienti.get(i);
            ListaAcquistoResult listaAcquistoResult = ListaAcquistoBusiness.getInstance().caricaListeAcquisto(c.getUsername());
            ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoResult.getListeAcquisto();
            int j=i;
            while (j < listeAcquisto.size()){
                RigaListaAcquisto riga = new RigaListaAcquisto();
                JButton visualizzaButton = new JButton("Visualizza");
                JButton eliminaButton = new JButton("Elimina");
                JButton pagataButton = new JButton("Pagata");
                riga.setUsernameCliente(c.getUsername());
                riga.setNomeLista(listeAcquisto.get(j).getNome());
                riga.setPagata(pagataButton);
                riga.setVisualizzaButton(visualizzaButton);
                riga.setEliminaButton(eliminaButton);
                j++;
                righe.add(riga);
            }


            //aggiungere action listener

        }

        ListaAcquistoTableModel tableModel = new ListaAcquistoTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
        tabella.getColumn("Visualizza").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(new JTableButtonMouseListener(tabella));

        tabella.getColumn("Elimina").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(new JTableButtonMouseListener(tabella));

        tabella.getColumn("Stato Pagamento").setCellRenderer(buttonRenderer);
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
