package View.Decorator;

import View.Listener.GoToCatalogoListener;
import View.Listener.SfogliaCatalogoProdottiListener;
import View.MainFrame;

import javax.swing.*;

public class GuestMenu extends Menu {

    private MainFrame frame;

    public GuestMenu(MainFrame frame) {

        this.frame = frame;
        JButton sfogliaCatalogoProdotti = new JButton("Sfoglia catalogo prodotti");
        sfogliaCatalogoProdotti.addActionListener(new SfogliaCatalogoProdottiListener(this.frame));
        JButton sfogliaCatalogoServizi = new JButton("Sfoglia catalogo servizi");
        sfogliaCatalogoServizi.addActionListener(new GoToCatalogoListener(this.frame));

        pulsanti.add(sfogliaCatalogoProdotti);
        pulsanti.add(sfogliaCatalogoServizi);

    }
}
