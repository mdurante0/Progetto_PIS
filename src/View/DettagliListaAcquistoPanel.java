package View;

import Business.ArticoloBusiness;
import Business.Results.ArticoloResult;
import Model.*;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import View.Listener.*;

import javax.swing.*;
import java.awt.*;

public class DettagliListaAcquistoPanel extends JPanel {
    private MainFrame frame;
    private ListaAcquisto listaAcquisto;
    private JPanel titlePanel = new JPanel();

    private JPanel contentPanel = new JPanel();

    public DettagliListaAcquistoPanel(MainFrame frame, ListaAcquisto listaAcquisto) {
        this.frame = frame;
        this.listaAcquisto = listaAcquisto;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Dettagli Lista ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(0,3));
        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        Font bodyFont1 = new Font(Font.DIALOG, Font.BOLD, 20);

        //nome lista
        JLabel nomeLabel = new JLabel("  Nome lista:    "+listaAcquisto.getNome());
        nomeLabel.setFont(bodyFont1);
        contentPanel.add(nomeLabel);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
    /*    contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());*/

        float costoTotale = 0;
        //Articoli
        for (int i = 0; i < listaAcquisto.getArticoli().size(); i++) {
            JLabel articoloLabel = new JLabel("     Articolo:       " + this.listaAcquisto.getArticoli().get(i).getName() );
            JLabel quantitaLabel = new JLabel();
            JLabel prezzoLabel = new JLabel();
            if(!ArticoloBusiness.getInstance().getType(listaAcquisto.getArticoli().get(i)).getResult().equals(ArticoloResult.Result.IS_SERVIZIO)){
                quantitaLabel.setText("     Quantità articolo:      " + listaAcquisto.getArticoli().get(i).getQuantita() );
                prezzoLabel.setText ("    Prezzo:    €" + listaAcquisto.getArticoli().get(i).getQuantita()*listaAcquisto.getArticoli().get(i).getPrezzo());
                costoTotale += listaAcquisto.getArticoli().get(i).getQuantita()*listaAcquisto.getArticoli().get(i).getPrezzo();


            }else  {
                prezzoLabel.setText ("    Prezzo:    €" + listaAcquisto.getArticoli().get(i).getPrezzo());
                costoTotale += listaAcquisto.getArticoli().get(i).getPrezzo();
            }



            articoloLabel.setFont(bodyFont);
            quantitaLabel.setFont(bodyFont);
            prezzoLabel.setFont(bodyFont);

            contentPanel.add(articoloLabel);
            contentPanel.add(quantitaLabel);
            contentPanel.add(prezzoLabel);
    /*        contentPanel.add(new JLabel());
            contentPanel.add(new JLabel());
            contentPanel.add(new JLabel());*/


        }
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());


        JLabel totale = new JLabel("    Totale:   €"+costoTotale);
        totale.setFont(bodyFont);
        contentPanel.add(totale);

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());

        JButton tornaIndietro = new JButton("Torna indietro");
        tornaIndietro.addActionListener(new GoToListaAcquistoListener(this.frame));
        tornaIndietro.setFont(bodyFont);

        contentPanel.add(tornaIndietro);

        this.add(titlePanel);
        this.add(contentPanel);
    }
}
