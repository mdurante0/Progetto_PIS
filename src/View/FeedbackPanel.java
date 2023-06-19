package View;

import Business.FeedbackBusiness;
import Business.Results.FeedbackResult;
import Business.SessionManager;
import Business.Strategy.*;
import Model.*;
import Model.composite.ProdottoComposito;
import View.Listener.GoToDettagliComponenteListener;
import View.Listener.GoToDettagliListener;
import View.ViewModel.FeedbackTableModel;
import View.ViewModel.RigaFeedback;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FeedbackPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public FeedbackPanel(MainFrame frame, Articolo articolo, String nomePuntoVendita, ProdottoComposito prodottoComposito) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Commenti: " + articolo.getName());
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaFeedback> righe = new ArrayList<>();

        FeedbackResult result = FeedbackBusiness.getInstance().caricaFeedback(articolo.getName());
        ArrayList<Feedback> feedbacks = result.getFeedbacks();

        //ordinamento commenti in base al tipo di utente
        OrdinamentoCommenti ordinamentoCommenti = new OrdinamentoCommenti(feedbacks);
        IOrdinamentoCommentoStrategy ordinamento;
        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        if(u instanceof Amministratore || u instanceof Manager)
            ordinamento = new CommentiUrgentiStrategy();
        else if (u instanceof Cliente)
            ordinamento = new CommentiMiglioriStrategy();
        else
            ordinamento = new CommentiRecentiStrategy();

        ordinamentoCommenti.setOrdinamentoCommentoStrategy(ordinamento);
        ordinamentoCommenti.ordina();

        for(int i = 0 ; i < result.getFeedbacks().size(); i++){
            RigaFeedback riga = new RigaFeedback();
            Feedback f = feedbacks.get(i);
            riga.setData(f.getData());
            riga.setUsernameCliente(f.getCliente().getUsername());
            riga.setCommento(f.getCommento());
            riga.setPunteggio(f.getGradimento());
            riga.setRisposta(f.getRisposta());
            righe.add(riga);
        }

        FeedbackTableModel tableModel = new FeedbackTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(100);
        tabella.setAutoscrolls(true);
        tabella.setRowSelectionAllowed(false);
        tabella.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(tabella);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        contentPanel.add(new JLabel("          "), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(new JLabel("          "), BorderLayout.EAST);

        JPanel pulsantiAzioneTabella = new JPanel();
        pulsantiAzioneTabella.setLayout(new FlowLayout());
        JButton tornaIndietroButton = new JButton("Torna indietro");
        if(prodottoComposito == null)
            tornaIndietroButton.addActionListener(new GoToDettagliListener(this.frame, articolo, nomePuntoVendita));
        else
            tornaIndietroButton.addActionListener(new GoToDettagliComponenteListener(this.frame, articolo, prodottoComposito, nomePuntoVendita));

        southPanel.add(tornaIndietroButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
