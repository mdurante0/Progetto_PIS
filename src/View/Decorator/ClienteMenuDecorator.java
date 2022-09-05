package View.Decorator;

import javax.swing.*;
import java.util.List;

public class ClienteMenuDecorator extends CustomMenuDecorator {

    public ClienteMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<JButton> getPulsanti() {

        // ha tutte le funzioni del guest
        pulsanti.addAll(this.menu.getPulsanti());

        // + le funzioni del cliente
        JButton liste = new JButton("Le mie liste");
        //action command, listener...
        pulsanti.add(liste);

        return pulsanti;
    }
}
