package View.Decorator;

import View.Listener.SfogliaCatalogoListener;
import View.MainFrame;

import javax.swing.*;

public class GuestMenu extends Menu {

    private MainFrame frame;

    public GuestMenu(MainFrame frame) {

        this.frame = frame;
        JButton sfoglia = new JButton("Sfoglia catalogo");
        sfoglia.addActionListener(new SfogliaCatalogoListener(this.frame));

        pulsanti.add(sfoglia);

    }
}
