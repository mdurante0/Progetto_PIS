package View;

import Business.ClienteBusiness;
import Business.PrenotazioneBusiness;
import Business.Results.ClienteResult;
import Business.Results.PrenotazioneResult;
import Model.Cliente;
import Model.Prenotazione;
import Model.PuntoVendita;
import View.Listener.GoToCreaPrenotazioneListener;
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

    public MostraPrenotazioniPanel(MainFrame frame, PuntoVendita puntoVendita, Cliente cliente ) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Prenotazioni ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaPrenotazione> righe = new ArrayList<>();



            PrenotazioneResult prenotazioneResult = PrenotazioneBusiness.getInstance().caricaPrenotazioniByUser(cliente.getUsername());
            ArrayList<Prenotazione> prenotazioniCliente = prenotazioneResult.getPrenotazioni();

            for(int i=0; i < prenotazioniCliente.size();i++){
                RigaPrenotazione riga = new RigaPrenotazione();

                for(int j=0; j < prenotazioniCliente.get(i).getProdotti().size(); j++ ){
                    JButton elimina = new JButton("Elimina");
                    riga.setUsernameCliente(cliente.getUsername());
                    riga.setNomeProdotto(prenotazioniCliente.get(i).getProdotti().get(j).getName());
                    riga.setQuantitaProdotto(prenotazioniCliente.get(i).getProdotti().get(j).getQuantita());
                    riga.setData(prenotazioniCliente.get(i).getDataPrenotazione());
                    riga.setEliminaButton(elimina);

                    righe.add(riga);
                }

            }


            //aggiungere action listener



        PrenotazioneTableModel tableModel = new PrenotazioneTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
        JTableButtonMouseListener mouseListener = new JTableButtonMouseListener(tabella);
        tabella.getColumn("Elimina").setCellRenderer(buttonRenderer);
        tabella.addMouseListener(mouseListener);


        contentPanel.add(new JLabel("          "), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(new JLabel("          "), BorderLayout.EAST);

        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));

        JButton aggiungiPrenotazione = new JButton("Crea Prenotazione");
        aggiungiPrenotazione.addActionListener(new GoToCreaPrenotazioneListener(this.frame, puntoVendita));

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
