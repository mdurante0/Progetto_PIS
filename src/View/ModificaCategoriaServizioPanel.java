package View;


import Model.CategoriaServizio;

import javax.swing.*;
import java.awt.*;

public class ModificaCategoriaServizioPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;


    public ModificaCategoriaServizioPanel(MainFrame frame, CategoriaServizio categoriaServizio) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Modifica Categoria Servizio");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(5,2));
        JLabel categoriaLabel = new JLabel("  Categoria:");

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        categoriaLabel.setFont(bodyFont);

        categoriaField = new JTextField(categoriaServizio.getNome(),20);



        categoriaField.setFont(bodyFont);

        JButton aggiungiCategoriaProdottoButton = new JButton("Modifica");
        aggiungiCategoriaProdottoButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
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
