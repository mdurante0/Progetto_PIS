package View;

import Business.ArticoloBusiness;
import Business.Results.ArticoloResult;
import Model.ListaAcquisto;
import View.Listener.GoToListaAcquistoListener;
import View.Listener.JTableButtonMouseListener;
import View.Listener.RemoveArticoloFromListaAcquistoListener;
import View.ViewModel.DettagliListaAcquistoTableModel;
import View.ViewModel.RigaDettagliListaAcquisto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VisualizzaListaAcquistoPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public VisualizzaListaAcquistoPanel(MainFrame frame, ListaAcquisto listaAcquisto) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Lista di acquisto:  "+listaAcquisto.getNome());
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaDettagliListaAcquisto> righe = new ArrayList<>();

        for (int i = 0; i < listaAcquisto.getArticoli().size(); i++) {
            RigaDettagliListaAcquisto rigaDettagliListaAcquisto = new RigaDettagliListaAcquisto();
            JButton eliminaButton = new JButton("Elimina");
            eliminaButton.addActionListener(new RemoveArticoloFromListaAcquistoListener(this.frame, listaAcquisto, listaAcquisto.getArticoli().get(i)));
            rigaDettagliListaAcquisto.setNomeProdotto(listaAcquisto.getArticoli().get(i).getName());

            ArticoloResult articoloResult = ArticoloBusiness.getInstance().getType(listaAcquisto.getArticoli().get(i));
            if (!articoloResult.getResult().equals(ArticoloResult.Result.IS_SERVIZIO)){
                rigaDettagliListaAcquisto.setQuantita(String.valueOf(listaAcquisto.getArticoli().get(i).getQuantita()));
                rigaDettagliListaAcquisto.setCosto(listaAcquisto.getArticoli().get(i).getPrezzo()*listaAcquisto.getArticoli().get(i).getQuantita());
            }
            else {
                rigaDettagliListaAcquisto.setQuantita("");
                rigaDettagliListaAcquisto.setCosto(listaAcquisto.getArticoli().get(i).getPrezzo());
            }
            rigaDettagliListaAcquisto.setEliminaButton(eliminaButton);
            righe.add(rigaDettagliListaAcquisto);
        }

        DettagliListaAcquistoTableModel tableModel = new DettagliListaAcquistoTableModel(righe);
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
        tornaIndietroButton.addActionListener(new GoToListaAcquistoListener(this.frame));
        JButton acquistaButton = new JButton("Acquista");

        //aggiungere action listener

        southPanel.setLayout(new FlowLayout());
        southPanel.add(tornaIndietroButton);
        southPanel.add(acquistaButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
