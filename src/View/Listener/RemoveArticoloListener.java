package View.Listener;

import Business.ArticoloBusiness;
import Business.CollocazioneBusiness;
import Business.Results.ArticoloResult;
import Business.Results.CollocazioneResult;
import Model.Articolo;
import Model.composite.Prodotto;
import View.CatalogoProdottiPanel;
import View.CatalogoServiziPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveArticoloListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private String nomePuntoVendita;

    public RemoveArticoloListener(MainFrame frame, Articolo articolo, String nomePuntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.nomePuntoVendita = nomePuntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questo articolo?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            ArticoloResult result = ArticoloBusiness.getInstance().removeArticolo(articolo);
            if(articolo instanceof Prodotto prodotto) {
                prodotto.getCollocazione().setMagazzino(prodotto.getMagazzino());
                CollocazioneResult collocazioneResult = CollocazioneBusiness.getInstance().removeCollocazione(prodotto.getCollocazione());
                if(!collocazioneResult.getResult().equals(CollocazioneResult.Result.DELETE_OK)){
                    JOptionPane.showMessageDialog(this.frame, collocazioneResult.getMessage());
                }
            }

            if(result.getResult().equals(ArticoloResult.Result.DELETE_OK)){
                if(nomePuntoVendita != null)
                    this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, nomePuntoVendita));
                else
                    this.frame.mostraPannelloAttuale(new CatalogoServiziPanel(this.frame));
            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
