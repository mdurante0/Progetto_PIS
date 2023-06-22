package View.Decorator;

import View.Listener.GoToMostraClientiListener;
import View.MainFrame;

import javax.swing.*;
import java.util.List;

public class ManagerMenuDecorator extends CustomMenuDecorator {

    public ManagerMenuDecorator(MainFrame frame) {
        this.frame = frame;
        this.menu = new ClienteMenuDecorator(this.frame);
    }

    @Override
    public List<JButton> getPulsanti() {

        // ha tutte le funzioni del cliente
        pulsanti.addAll(this.menu.getPulsanti());

        // + le funzioni del manager
        JButton clienti = new JButton("Mostra clienti");

        clienti.addActionListener(new GoToMostraClientiListener(this.frame));


        //add pulsanti
        pulsanti.add(clienti);

        return pulsanti;
    }
}
