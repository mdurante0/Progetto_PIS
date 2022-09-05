package View;

import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CatalogoPanel extends JPanel {

    public CatalogoPanel() {

        //JLabel benvenuto = new JLabel("Qui mostreremo la tabella dei prodotti");
        //add(benvenuto);

        setLayout(new BorderLayout());

        String[][] dati = new String[3][5];
        dati[0][0] = "a";
        dati[0][1] = "b";
        dati[0][2] = "c";
        dati[0][3] = "d";
        dati[0][4] = "e";
        dati[1][0] = "f";
        dati[1][1] = "g";
        dati[1][2] = "h";
        dati[1][3] = "i";
        dati[1][4] = "j";
        dati[2][0] = "k";
        dati[2][1] = "l";
        dati[2][2] = "m";
        dati[2][3] = "n";
        dati[2][4] = "o";
        String[] nomiColonne = new String[] {"Colonna 1", "Colonna 2", "Colonna 3", "Colonna 4", "Colonna 5"};

        //JTable tabella = new JTable(dati, nomiColonne);

        java.util.List<RigaCatalogo> righe = new ArrayList<RigaCatalogo>();
        //popoliamo con dati fake (voi dovete popolare tramite DAO)
        RigaCatalogo riga1 = new RigaCatalogo();
        riga1.setIdProdotto(759);
        riga1.setNomeProdotto("Tavolo giardino");
        riga1.setNomeProduttore("Produttore X");
        riga1.setNomeCategoria("Esterni");
        riga1.setPrezzo(99.9F);

        RigaCatalogo riga2 = new RigaCatalogo();
        riga2.setIdProdotto(88);
        riga2.setNomeProdotto("Sedia");
        riga2.setNomeProduttore("Produttore Y");
        riga2.setNomeCategoria("Casa");
        riga2.setPrezzo(50.0F);

        RigaCatalogo riga3 = new RigaCatalogo();
        riga3.setIdProdotto(54);
        riga3.setNomeProdotto("Tosaerba");
        riga3.setNomeProduttore("Produttore Z");
        riga3.setNomeCategoria("Utensili");
        riga3.setPrezzo(350.0F);

        righe.add(riga1);
        righe.add(riga2);
        righe.add(riga3);

        CatalogoTableModel tableModel = new CatalogoTableModel(righe);
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(200);

        JScrollPane scrollPane = new JScrollPane(tabella);
        add(scrollPane, BorderLayout.CENTER);

        JPanel pulsantiAzioneTabella = new JPanel();
        pulsantiAzioneTabella.setLayout(new FlowLayout());
        JButton mettiNelCarrello = new JButton("Metti nel carrello");

        mettiNelCarrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] righeSelezionate = tabella.getSelectedRows();
                System.out.println(righeSelezionate[0]);
                CatalogoTableModel tModel = (CatalogoTableModel) tabella.getModel();
                for(int i=0;i<righeSelezionate.length;i++) {
                    RigaCatalogo rigaSelezionata = tModel.getRighe().get(righeSelezionate[i]);
                    System.out.println("ID prodotto selezionato: "+rigaSelezionata.getIdProdotto());
                }
            }
        });

        pulsantiAzioneTabella.add(mettiNelCarrello);
        add(mettiNelCarrello, BorderLayout.SOUTH);


    }
}
