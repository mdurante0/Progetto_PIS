package View.Listener;

import Business.ArticoloBusiness;
import Business.CollocazioneBusiness;
import Business.Results.ArticoloResult;
import Model.Articolo;
import Model.Collocazione;
import Model.PuntoVendita;
import Model.composite.IProdotto;
import View.CatalogoProdottiPanel;
import View.CatalogoServiziPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveArticoloListener implements ActionListener {
    private MainFrame frame;
    private Articolo articolo;
    private PuntoVendita puntoVendita;

    public RemoveArticoloListener(MainFrame frame, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler eliminare questo articolo?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if(confirmed == 0) {
            ArticoloResult result = ArticoloBusiness.getInstance().removeArticolo(articolo);
            if(result.getResult().equals(ArticoloResult.Result.DELETE_OK)) {

                if (articolo instanceof IProdotto prodotto) {

                    for (Collocazione collocazione :
                            CollocazioneBusiness.getInstance().caricaCollocazioniByProdotto(prodotto).getCollocazioni()) {
                        CollocazioneBusiness.getInstance().removeCollocazione(collocazione);
                    }

                    if (prodotto.getMagazzino().getIndirizzo() != null) {
                        this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, puntoVendita));
                    } else {
                        this.frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, null));
                    }
                } else
                    this.frame.mostraPannelloAttuale(new CatalogoServiziPanel(this.frame));
            }
            JOptionPane.showMessageDialog(this.frame, result.getMessage());
        }
    }
}
