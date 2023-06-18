package View;


import View.Listener.CreaCategoriaServizioListener;
import View.Listener.GoToMenuListener;

import javax.swing.*;
import java.awt.*;

public class CreaCategoriaServizioPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;


    public CreaCategoriaServizioPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Nuova Categoria Servizio");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(5,2));
        JLabel categoriaLabel = new JLabel("  Categoria:");

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        categoriaLabel.setFont(bodyFont);

        categoriaField = new JTextField(20);



        categoriaField.setFont(bodyFont);

        JButton aggiungiCategoriaProdottoButton = new JButton("Aggiungi");
        aggiungiCategoriaProdottoButton.addActionListener(new CreaCategoriaServizioListener(this.frame, categoriaField));
        aggiungiCategoriaProdottoButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));
        tornaIndietroButton.setFont(bodyFont);

        // aggiungere gli action listener

        contentPanel.add(categoriaLabel);
        contentPanel.add(categoriaField);

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(aggiungiCategoriaProdottoButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
