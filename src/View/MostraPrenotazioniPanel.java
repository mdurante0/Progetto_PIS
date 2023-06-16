package View;

import Business.ClienteBusiness;
import Business.MagazzinoBusiness;
import Business.PrenotazioneBusiness;
import Business.Results.ClienteResult;
import Business.Results.MagazzinoResult;
import Business.Results.PrenotazioneResult;
import Model.Cliente;
import Model.Prenotazione;
import View.Listener.GoToMenuListener;
import View.Listener.JTableButtonMouseListener;
import View.ViewModel.PrenotazioneTableModel;
import View.ViewModel.RigaPrenotazione;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraPrenotazioniPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraPrenotazioniPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Prenotazioni ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaPrenotazione> righe = new ArrayList<>();


        ClienteResult clienteResult = ClienteBusiness.getInstance().caricaClienti();
        ArrayList<Cliente> clienti = clienteResult.getClienti();

        for(int i = 0 ; i < clienteResult.getClienti().size(); i++){

            Cliente c = clienti.get(i);
            PrenotazioneResult prenotazioneResult = PrenotazioneBusiness.getInstance().caricaPrenotazioniByUser(c.getUsername());
            Prenotazione p = prenotazioneResult.getPrenotazioni().get(i);
            int j=i;
            while (j < p.getProdotti().size()){
                RigaPrenotazione riga = new RigaPrenotazione();
                JButton modifica = new JButton("Modifica");
                JButton elimina = new JButton("Elimina");
                riga.setUsernameCliente(c.getUsername());
                riga.setNomeProdotto(p.getProdotti().get(j).getName());
                riga.setQuantitaProdotto(p.getProdotti().get(j).getQuantita());
                riga.setData(p.getDataPrenotazione());
                riga.setModificaButton(modifica);
                riga.setEliminaButton(elimina);
                j++;
                righe.add(riga);
            }


            //aggiungere action listener

        }

        PrenotazioneTableModel tableModel = new PrenotazioneTableModel(righe);
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
