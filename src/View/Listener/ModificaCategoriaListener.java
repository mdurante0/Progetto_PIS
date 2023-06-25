package View.Listener;

import Business.AbstractFactory.ICategoria;
import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaProdotto;
import View.MainFrame;
import View.MostraCategoriaProdottoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificaCategoriaListener implements ActionListener {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField categoriaField;
    private JComboBox<String> categoriaPadre;

    private ICategoria categoria;

    public ModificaCategoriaListener(MainFrame frame, JTextField categoriaField, JComboBox<String> categoriaPadre, ICategoria categoria) {
        this.frame = frame;
        this.categoriaField = categoriaField;
        this.categoriaPadre = categoriaPadre;
        this.categoria = categoria;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        categoria.setNome(categoriaField.getText());

        if(categoria instanceof CategoriaProdotto categoriaProdotto) {
            if (!categoriaPadre.getSelectedItem().toString().equals("Nessuna sopra categoria"))
                categoriaProdotto.setIdCategoriaProdottoParent(CategoriaBusiness.getInstance().caricaCategoriaProdottoByName(categoriaPadre.getSelectedItem().toString()).getCategorieProdotto().get(0).getIdCategoriaProdottoParent());
            else categoriaProdotto.setIdCategoriaProdottoParent(0);
            categoria = categoriaProdotto;
        }
        CategoriaResult categoriaResult = CategoriaBusiness.getInstance().updateCategoria(categoria);
        if(categoriaResult.getResult().equals(CategoriaResult.Result.UPDATE_OK))
            this.frame.mostraPannelloAttuale(new MostraCategoriaProdottoPanel(this.frame));
        JOptionPane.showMessageDialog(this.frame, categoriaResult.getMessage());

    }
}
