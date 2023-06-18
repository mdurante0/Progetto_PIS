package View;


import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaProdotto;
import View.Listener.CreaCategoriaProdottoListener;
import View.Listener.GoToMenuListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreaCategoriaProdottoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;
    private JComboBox<String> categoriaParentBox;

    public CreaCategoriaProdottoPanel(MainFrame frame) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Nuova Categoria prodotto");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(7,2));
        JLabel categoriaLabel = new JLabel("  Categoria:");
        JLabel sottocategorieLabel = new JLabel("  Categoria Padre:");

        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        categoriaLabel.setFont(bodyFont);
        sottocategorieLabel.setFont(bodyFont);

        categoriaField = new JTextField(20);

        CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategorieProdotto();
        ArrayList<CategoriaProdotto> categorieProdotti = categoriaResult.getCategorieProdotto();
        String[] nomiCategorie = new String[categorieProdotti.size() + 1];
        for(int i = 0; i < categorieProdotti.size(); i++ ){

             nomiCategorie[i] = categorieProdotti.get(i).getNome();

        }
        categoriaParentBox = new JComboBox<>(nomiCategorie);
        categoriaParentBox.setFocusable(false);
        categoriaParentBox.setFont(bodyFont);

        categoriaField.setFont(bodyFont);
        categoriaParentBox.setFont(bodyFont);

        JButton aggiungiCategoriaProdottoButton = new JButton("Aggiungi");
        aggiungiCategoriaProdottoButton.addActionListener(new CreaCategoriaProdottoListener(this.frame, categoriaField,categoriaParentBox));
        aggiungiCategoriaProdottoButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));
        tornaIndietroButton.setFont(bodyFont);

        // aggiungere gli action listener

        contentPanel.add(categoriaLabel);
        contentPanel.add(categoriaField);
        contentPanel.add(sottocategorieLabel);
        contentPanel.add(categoriaParentBox);
        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(aggiungiCategoriaProdottoButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
