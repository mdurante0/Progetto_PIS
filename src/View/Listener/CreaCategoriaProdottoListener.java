package View.Listener;


import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaProdotto;
import View.MainFrame;
import View.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaCategoriaProdottoListener implements ActionListener {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;
    private JComboBox categoriaParentBox;
    private CategoriaProdotto categoriaProdotto = new CategoriaProdotto();

    public CreaCategoriaProdottoListener(MainFrame frame, JTextField categoriaField, JComboBox categoriaParentBox) {
        this.frame = frame;
        this.categoriaField = categoriaField;
        this.categoriaParentBox = categoriaParentBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        categoriaProdotto.setNome(categoriaField.getText());
        if (!categoriaProdotto.getNome().isEmpty()) {

            if (categoriaParentBox.getSelectedItem() == null) {
                CategoriaResult categoriaResult = CategoriaBusiness.getInstance().addCategoria(categoriaProdotto);
                if (categoriaResult.getResult().equals(CategoriaResult.Result.ADD_OK))
                    this.frame.mostraPannelloAttuale(new MenuPanel(this.frame));

                JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());
            }
            if (categoriaParentBox.getSelectedItem() != null) {
                CategoriaResult categoriaResult1 = CategoriaBusiness.getInstance().caricaCategoriaProdottoByName(categoriaParentBox.getSelectedItem().toString());
                CategoriaProdotto categoriaProdottoParent = categoriaResult1.getCategorieProdotto().get(0);
                categoriaProdotto.setIdCategoriaProdottoParent(categoriaProdottoParent.getIdCategoria());
                CategoriaResult categoriaResult = CategoriaBusiness.getInstance().addCategoria(categoriaProdotto);
                if (categoriaResult.getResult().equals(CategoriaResult.Result.ADD_OK))
                    this.frame.mostraPannelloAttuale(new MenuPanel(this.frame));

                JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());


            }

        } else JOptionPane.showMessageDialog(this.frame, "Attenzione: il campo deve contenere almeno un nome!");
    }

}
