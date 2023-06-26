package View.Listener;

import View.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SfogliaListener implements ActionListener {
    private MainFrame frame;
    private JLabel allegatoLabel;

    public SfogliaListener(MainFrame frame, JLabel allegatoLabel) {
        this.frame = frame;
        this.allegatoLabel = allegatoLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setCurrentDirectory(new File("resources"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"png", "jpeg", "jpg", "pdf", "txt");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            MailListener.setAllegatoPath(file.getAbsolutePath());
            allegatoLabel.setText("File allegato: " + file.getName());
            frame.repaint();
            frame.revalidate();
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(frame, "No Data");
        }
    }
}
