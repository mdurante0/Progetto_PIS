package View.Listener;

import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaProdotto;
import View.MainFrame;
import View.MostraCategoriaProdottoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificaCategoriaProdottoListener implements ActionListener {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;
    private JComboBox<String> categoriaPadre;

    private CategoriaProdotto categoriaProdotto;

    public ModificaCategoriaProdottoListener(MainFrame frame, JTextField categoriaField, JComboBox<String> categoriaPadre, CategoriaProdotto categoriaProdotto) {
        this.frame = frame;
        this.categoriaField = categoriaField;
        this.categoriaPadre = categoriaPadre;
        this.categoriaProdotto = categoriaProdotto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        categoriaProdotto.setNome(categoriaField.getText());
        if(categoriaPadre.getSelectedItem().toString() != "Nessuna sopra categoria" )
             categoriaProdotto.setIdCategoriaProdottoParent(CategoriaBusiness.getInstance().caricaCategoriaProdottoByName(categoriaPadre.getSelectedItem().toString()).getCategorieProdotto().get(0).getIdCategoriaProdottoParent());
        else categoriaProdotto.setIdCategoriaProdottoParent(0);
            CategoriaResult categoriaResult = CategoriaBusiness.getInstance().updateCategoria(categoriaProdotto);
            if(categoriaResult.getResult() == CategoriaResult.Result.UPDATE_OK)
                this.frame.mostraPannelloAttuale(new MostraCategoriaProdottoPanel(this.frame));
            JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());

    }
}
