package View.Listener;


import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaServizio;
import View.MainFrame;
import View.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaCategoriaServizioListener implements ActionListener {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;
    private CategoriaServizio categoriaServizio = new CategoriaServizio();

    public CreaCategoriaServizioListener(MainFrame frame, JTextField categoriaField) {
        this.frame = frame;
        this.categoriaField = categoriaField;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        categoriaServizio.setNome(categoriaField.getText());

        if (!categoriaServizio.getNome().isEmpty()) {
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().addCategoria(categoriaServizio);
            if (categoriaResult.getResult().equals(CategoriaResult.Result.ADD_OK))
                this.frame.mostraPannelloAttuale(new MenuPanel(this.frame));
            JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());

        }
        else JOptionPane.showMessageDialog(this.frame, "Attenzione: il campo deve contenere almeno un nome!");

    }

}

