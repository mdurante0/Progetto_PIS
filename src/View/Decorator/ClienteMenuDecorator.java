package View.Decorator;

import View.MainFrame;

import javax.swing.*;
import java.util.List;

public class ClienteMenuDecorator extends CustomMenuDecorator {

    public ClienteMenuDecorator(MainFrame frame) {
        this.frame = frame;
        this.menu = new GuestMenu(this.frame);
    }

    @Override
    public List<JButton> getPulsanti() {

        // ha tutte le funzioni del guest
        pulsanti.addAll(this.menu.getPulsanti());

        // + le funzioni del cliente
        JButton liste = new JButton("Liste d'acquisto");
        JButton prenotazioni = new JButton("Prenotazioni");
        JButton feedback = new JButton("Feedback");

        //action command, listener...


        //add dei pulsanti
        pulsanti.add(liste);
        pulsanti.add(prenotazioni);
        pulsanti.add(feedback);

        return pulsanti;
    }
}
