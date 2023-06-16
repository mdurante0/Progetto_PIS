package View.Listener;

import Model.Immagine;
import View.NuovoProdottoPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ImmaginiListener implements ActionListener {
    private NuovoProdottoPanel panel;
    private ArrayList<Immagine> immagini;
    public static final String AGGIUNGI = "aggiungi";
    public static final String RIMUOVI = "rimuovi";
    public ImmaginiListener(NuovoProdottoPanel panel, ArrayList<Immagine> immagini) {
        this.panel = panel;
        this.immagini = immagini;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(AGGIUNGI)) {
            JFileChooser fileChooser = new JFileChooser();
            //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setCurrentDirectory(new File("C:\\Users\\matti\\IdeaProjects\\MyShop\\resources"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "gif", "png", "jpeg", "pdf", "jpg");
            fileChooser.addChoosableFileFilter(filter);
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                Immagine immagine = new Immagine();
                ImageIcon image = new ImageIcon(selectedFile.getAbsolutePath());
                immagine.setPic(image);
                immagini.add(immagine);
                panel.AggiornaCounter();
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(panel, "No Data");
            }
        }
        else if (e.getActionCommand().equals(RIMUOVI) && !immagini.isEmpty()){
            immagini.remove(immagini.size()-1);
            panel.AggiornaCounter();
        }
    }
}
