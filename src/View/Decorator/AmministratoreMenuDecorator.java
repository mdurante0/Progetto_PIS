package View.Decorator;

import View.Listener.*;
import View.MainFrame;

import javax.swing.*;
import java.util.List;

public class AmministratoreMenuDecorator extends CustomMenuDecorator {

    public AmministratoreMenuDecorator(MainFrame frame) {
        this.frame = frame;
        this.menu = new ManagerMenuDecorator(this.frame);
    }

    @Override
    public List<JButton> getPulsanti() {

        // ha tutte le funzioni del manager
        pulsanti.addAll(this.menu.getPulsanti());

        // + le funzioni dell'amministratore
        JButton nuovoProdotto = new JButton("Crea un nuovo prodotto");
        JButton nuovoProdottoComposito = new JButton("Crea un nuovo prodotto composto");
        JButton nuovoServizio = new JButton("Crea un nuovo servizio");
        JButton categorieProdotti = new JButton("Categorie prodotti");
        JButton categorieServizi = new JButton("Categorie servizi");
        JButton produttore = new JButton("Produttori");
        JButton fornitore = new JButton("Fornitori");
        JButton puntoVendita = new JButton("Punti Vendita");
        JButton manager = new JButton("Mostra Manager");
        JButton magazziniButton = new JButton("Magazzini");

        //action command, listener...
        nuovoProdotto.addActionListener(new GoToCreaProdottoListener(this.frame));
        nuovoProdottoComposito.addActionListener(new GoToCreaProdottoCompositoListener(this.frame));
        nuovoServizio.addActionListener(new GoToCreaServizioListener(this.frame));
        produttore.addActionListener(new GoToMostraProduttoriListener(this.frame));
        fornitore.addActionListener(new GoToMostraFornitoriListener(this.frame));
        categorieProdotti.addActionListener(new GoToMostraCategorieProdottiListener(this.frame));
        categorieServizi.addActionListener(new GoToMostraCategorieServiziListener(this.frame));
        magazziniButton.addActionListener(new GoToMostraMagazziniListener(this.frame));
        manager.addActionListener(new GoToMostraManagerListener(this.frame));
        puntoVendita.addActionListener(new GoToMostraPuntiVenditaListener(this.frame));

        //add pulsanti
        pulsanti.add(nuovoProdotto);
        pulsanti.add(nuovoProdottoComposito);
        pulsanti.add(nuovoServizio);
        pulsanti.add(categorieProdotti);
        pulsanti.add(categorieServizi);
        pulsanti.add(produttore);
        pulsanti.add(fornitore);
        pulsanti.add(puntoVendita);
        pulsanti.add(manager);
        pulsanti.add(magazziniButton);

        return pulsanti;
    }
}
