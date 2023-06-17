package View.Listener;

import View.NuovoProdottoPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ImmaginiListener implements ActionListener {
    private NuovoProdottoPanel panel;
    private ArrayList<File> files;
    public static final String AGGIUNGI = "aggiungi";
    public static final String RIMUOVI = "rimuovi";
    public ImmaginiListener(NuovoProdottoPanel panel, ArrayList<File> files) {
        this.panel = panel;
        this.files = files;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(AGGIUNGI)) {
            JFileChooser fileChooser = new JFileChooser();
            //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setCurrentDirectory(new File("C:\\Users\\matti\\IdeaProjects\\MyShop\\resources"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "gif", "png", "jpeg", "pdf", "jpg");
            fileChooser.addChoosableFileFilter(filter);
            int result = fileChooser.showSaveDialog(panel);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                files.add(selectedFile);
                panel.AggiornaCounter();
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(panel, "No Data");
            }
        }
        else if (e.getActionCommand().equals(RIMUOVI) && !files.isEmpty()){
            files.remove(files.size()-1);
            panel.AggiornaCounter();
        }
    }
}
