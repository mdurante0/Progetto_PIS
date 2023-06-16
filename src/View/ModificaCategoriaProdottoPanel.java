package View;


import Model.CategoriaProdotto;

import javax.swing.*;
import java.awt.*;

public class ModificaCategoriaProdottoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;
    private JTextField sottocategoriaField;

    public ModificaCategoriaProdottoPanel(MainFrame frame, CategoriaProdotto categoriaProdotto) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Nuova Categoria prodotto");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(7,2));
        JLabel categoriaLabel = new JLabel("  Categoria:");
        JLabel sottocategorieLabel = new JLabel("  Sottocategoria:");

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        categoriaLabel.setFont(bodyFont);
        sottocategorieLabel.setFont(bodyFont);

        categoriaField = new JTextField(categoriaProdotto.getNome(),20);
        sottocategoriaField = new JTextField(categoriaProdotto.getSottocategorie().get(0).getNome(),20);


        categoriaField.setFont(bodyFont);
        sottocategoriaField.setFont(bodyFont);

        JButton aggiungiCategoriaProdottoButton = new JButton("Modifica");
        aggiungiCategoriaProdottoButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.setFont(bodyFont);

        // aggiungere gli action listener

        contentPanel.add(categoriaLabel);
        contentPanel.add(categoriaField);
        contentPanel.add(sottocategorieLabel);
        contentPanel.add(sottocategoriaField);

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(aggiungiCategoriaProdottoButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
