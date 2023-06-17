package View.Decorator;

import View.Listener.GoToNuovoProdottoCompositoListener;
import View.Listener.GoToNuovoProdottoListener;
import View.Listener.GoToNuovoServizioListener;
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
        JButton manager = new JButton("Manager");

        //action command, listener...
        nuovoProdotto.addActionListener(new GoToNuovoProdottoListener(this.frame));
        nuovoProdottoComposito.addActionListener(new GoToNuovoProdottoCompositoListener(this.frame));
        nuovoServizio.addActionListener(new GoToNuovoServizioListener(this.frame));

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

        return pulsanti;
    }
}
