package View.Decorator;

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
        JButton prodotto = new JButton("Nuovo Prodotto");
        JButton prodottoComposito = new JButton("Nuovo Prodotto Composto");
        JButton servizio = new JButton("Nuovo Servizio");
        JButton produttore = new JButton("Produttori");
        JButton fornitore = new JButton("Fornitori");
        JButton puntoVendita = new JButton("Punti Vendita");
        JButton manager = new JButton("Manager");

        //action command, listener...


        //add pulsanti
        pulsanti.add(prodotto);
        pulsanti.add(prodottoComposito);
        pulsanti.add(servizio);
        pulsanti.add(produttore);
        pulsanti.add(fornitore);
        pulsanti.add(puntoVendita);
        pulsanti.add(manager);

        return pulsanti;
    }
}
