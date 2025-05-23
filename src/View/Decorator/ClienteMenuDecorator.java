package View.Decorator;

import View.Listener.GoToFeedbackListener;
import View.Listener.GoToListaAcquistoListener;
import View.Listener.GoToMostraPrenotazioniListener;
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

        //action command, listener...
        prenotazioni.addActionListener(new GoToMostraPrenotazioniListener(this.frame));
        liste.addActionListener(new GoToListaAcquistoListener(this.frame));


        //add dei pulsanti
        pulsanti.add(liste);
        pulsanti.add(prenotazioni);

        return pulsanti;
    }
}
