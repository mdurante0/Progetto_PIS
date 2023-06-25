package View.Listener;

import Business.AbstractFactory.ICategoria;
import Business.CategoriaBusiness;
import Business.Results.CategoriaResult;
import Model.CategoriaProdotto;
import View.MainFrame;
import View.MostraCategoriaProdottoPanel;
import View.MostraCategoriaServizioPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCategoriaProdottoListener implements ActionListener {
    private MainFrame frame;
    private ICategoria categoria;

    public RemoveCategoriaProdottoListener(MainFrame frame, ICategoria categoria) {
        this.frame = frame;
        this.categoria = categoria;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questa categoria?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            CategoriaResult result = CategoriaBusiness.getInstance().removeCategoria(categoria);
            if(result.getResult().equals(CategoriaResult.Result.DELETE_OK)){
                if(categoria instanceof CategoriaProdotto)
                    this.frame.mostraPannelloAttuale(new MostraCategoriaProdottoPanel(this.frame));
                else this.frame.mostraPannelloAttuale(new MostraCategoriaServizioPanel(this.frame));
            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
