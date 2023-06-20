package View.Listener;

import Model.Articolo;
import View.ImagePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageListener implements ActionListener {
    private JPanel panel;
    private static int index;
    private ImagePanel imagePanel;
    private Articolo articolo;
    public static final String NEXT = "prossima";
    public static final String PREVIOUS = "precedente";
    public ImageListener(JPanel panel, ImagePanel imagePanel, Articolo articolo){
        this.panel = panel;
        this.imagePanel = imagePanel;
        this.articolo = articolo;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(NEXT)) {
            if(articolo.getImmagini() != null) {
                if (index < articolo.getImmagini().size() - 1) {
                    index++;
                    imagePanel.setImage(articolo.getImmagini().get(index).getPic().getImage());
                    panel.repaint();
                    panel.revalidate();
                }
            }
        } else if (e.getActionCommand().equals(PREVIOUS)) {
            if(articolo.getImmagini() != null) {
                if (index > 0) {
                    index--;
                    imagePanel.setImage(articolo.getImmagini().get(index).getPic().getImage());
                    panel.repaint();
                    panel.revalidate();
                }
            }
        }
    }
}
