package View.Listener;

import View.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ImmaginiListener implements ActionListener {
    private MainFrame frame;
    private ArrayList<File> files;
    private JLabel immaginiCounterLabel;
    public static final String AGGIUNGI = "aggiungi";
    public static final String RIMUOVI = "rimuovi";
    public ImmaginiListener(MainFrame frame, ArrayList<File> files, JLabel immaginiCounterLabel) {
        this.frame = frame;
        this.files = files;
        this.immaginiCounterLabel = immaginiCounterLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(AGGIUNGI)) {
            JFileChooser fileChooser = new JFileChooser();
            //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setCurrentDirectory(new File("resources"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE","png", "jpeg", "jpg");
            fileChooser.addChoosableFileFilter(filter);
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                files.add(selectedFile);
                immaginiCounterLabel.setText("  Immagini inserite: " + files.size());
                frame.repaint();
                frame.revalidate();
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(frame, "No Data");
            }
        }
        else if (e.getActionCommand().equals(RIMUOVI) && !files.isEmpty()){
            files.remove(files.size()-1);
            immaginiCounterLabel.setText("  Immagini inserite: " + files.size());
            frame.repaint();
            frame.revalidate();
        }
    }
}
