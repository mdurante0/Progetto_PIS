package View.Listener;

import Business.CategoriaBusiness;
import Business.FornitoreBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaProdotto;
import View.MainFrame;
import View.MostraCategoriaProdottoPanel;
import View.MostraFornitoriPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCategoriaProdottoListener implements ActionListener {
    private MainFrame frame;
    private CategoriaProdotto categoriaProdotto;

    public RemoveCategoriaProdottoListener(MainFrame frame, CategoriaProdotto categoriaProdotto) {
        this.frame = frame;
        this.categoriaProdotto = categoriaProdotto;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questa categoria?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            CategoriaResult result = CategoriaBusiness.getInstance().removeCategoria(categoriaProdotto);
            if(result.getResult().equals(CategoriaResult.Result.DELETE_OK)){
                    this.frame.mostraPannelloAttuale(new MostraCategoriaProdottoPanel(this.frame));
            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
