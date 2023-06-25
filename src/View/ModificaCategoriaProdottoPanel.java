package View;


import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaProdotto;
import View.Listener.GoToMostraCategorieProdottiListener;
import View.Listener.ModificaCategoriaProdottoListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModificaCategoriaProdottoPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;
    private JComboBox categoriaPadreBox;

    public ModificaCategoriaProdottoPanel(MainFrame frame, CategoriaProdotto categoriaProdotto) {
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

        categoriaField = new JTextField(categoriaProdotto.getNome(),20);
        CategoriaResult categoriaResult = CategoriaBusiness.getInstance().caricaCategorieProdotto();
        ArrayList<CategoriaProdotto> categorieProdotti = categoriaResult.getCategorieProdotto();
        String[] nomiCategorie = new String[categorieProdotti.size()+1];
        for (int i = 0; i < categorieProdotti.size(); i++) {
            nomiCategorie[i] = categorieProdotti.get(i).getNome();
        }
        nomiCategorie[nomiCategorie.length -1] = "Nessuna sopra categoria";
        categoriaPadreBox = new JComboBox(nomiCategorie);
        categoriaPadreBox.setFocusable(false);
        categoriaPadreBox.setSelectedItem(CategoriaBusiness.getInstance().caricaCategoriaProdottoById(categoriaProdotto.getIdCategoriaProdottoParent()).getCategorieProdotto().get(0).getNome());

        categoriaField.setFont(bodyFont);
        categoriaPadreBox.setFont(bodyFont);


        JButton aggiungiCategoriaProdottoButton = new JButton("Modifica");
        aggiungiCategoriaProdottoButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.setFont(bodyFont);

        // aggiungere gli action listener
        aggiungiCategoriaProdottoButton.addActionListener(new ModificaCategoriaProdottoListener(this.frame, categoriaField, categoriaPadreBox, categoriaProdotto));
        tornaIndietroButton.addActionListener(new GoToMostraCategorieProdottiListener(this.frame));

        contentPanel.add(categoriaLabel);
        contentPanel.add(categoriaField);
        contentPanel.add(sottocategorieLabel);
        contentPanel.add(categoriaPadreBox);

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(aggiungiCategoriaProdottoButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
