package View;


import Business.AbstractFactory.ICategoria;
import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaProdotto;
import View.Listener.GoToMostraCategorieProdottiListener;
import View.Listener.GoToMostraCategorieServiziListener;
import View.Listener.ModificaCategoriaListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModificaCategoriaPanel extends JPanel {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;
    private JComboBox categoriaPadreBox;

    public ModificaCategoriaPanel(MainFrame frame, ICategoria categoria) {
        this.frame = frame;

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Modifica Categoria");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        contentPanel.setLayout(new GridLayout(5,2));
        JLabel categoriaLabel = new JLabel("  Categoria:");
        JLabel sottocategorieLabel;
        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 20);
        categoriaLabel.setFont(bodyFont);
        categoriaField = new JTextField(categoria.getNome(),20);
        categoriaField.setFont(bodyFont);
        contentPanel.add(categoriaLabel);
        contentPanel.add(categoriaField);

        JButton modificaCategoriaButton = new JButton("Modifica");
        modificaCategoriaButton.setFont(bodyFont);
        JButton tornaIndietroButton = new JButton("Torna indietro");
        tornaIndietroButton.setFont(bodyFont);

        CategoriaResult categoriaResult;
        String[] nomiCategorie;
        if (categoria instanceof CategoriaProdotto categoriaProdotto) {
            sottocategorieLabel = new JLabel("  Sottocategoria di:");
            sottocategorieLabel.setFont(bodyFont);

            categoriaField = new JTextField(categoriaProdotto.getNome(),20);
            categoriaResult = CategoriaBusiness.getInstance().caricaCategorieProdotto();
            ArrayList<CategoriaProdotto> categorieProdotti = categoriaResult.getCategorieProdotto();
            nomiCategorie = new String[categorieProdotti.size()+1];
            for (int i = 0; i < categorieProdotti.size(); i++) {
                nomiCategorie[i] = categorieProdotti.get(i).getNome();
            }
            nomiCategorie[nomiCategorie.length -1] = "Nessuna categoria";
            categoriaPadreBox = new JComboBox(nomiCategorie);
            categoriaPadreBox.setFocusable(false);
            if (categoriaProdotto.getIdCategoriaProdottoParent() != 0)
                categoriaPadreBox.setSelectedItem(CategoriaBusiness.getInstance().caricaCategoriaProdottoById(categoriaProdotto.getIdCategoriaProdottoParent()).getCategorieProdotto().get(0).getNome());

            categoriaField.setFont(bodyFont);
            categoriaPadreBox.setFont(bodyFont);


            JButton aggiungiCategoriaProdottoButton = new JButton("Modifica");
            aggiungiCategoriaProdottoButton.setFont(bodyFont);
            tornaIndietroButton = new JButton("Torna indietro");
            tornaIndietroButton.setFont(bodyFont);

            aggiungiCategoriaProdottoButton.addActionListener(new ModificaCategoriaListener(this.frame, categoriaField, categoriaPadreBox, categoriaProdotto));
            tornaIndietroButton.addActionListener(new GoToMostraCategorieProdottiListener(this.frame));

            contentPanel.add(sottocategorieLabel);
            contentPanel.add(categoriaPadreBox);

            tornaIndietroButton.addActionListener(new GoToMostraCategorieProdottiListener(this.frame));
        }
        else tornaIndietroButton.addActionListener(new GoToMostraCategorieServiziListener(this.frame));
        modificaCategoriaButton.addActionListener(new ModificaCategoriaListener(this.frame, categoriaField, categoriaPadreBox, categoria));

        contentPanel.add(new JLabel());
        contentPanel.add(new JLabel());
        contentPanel.add(tornaIndietroButton);
        contentPanel.add(modificaCategoriaButton);

        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }



}
