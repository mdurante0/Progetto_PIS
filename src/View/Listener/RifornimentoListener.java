package View.Listener;

import Business.ArticoloBusiness;
import Business.MagazzinoBusiness;
import Business.PuntoVenditaBusiness;
import Business.Results.ArticoloResult;
import Business.Results.MagazzinoResult;
import Business.Results.PuntoVenditaResult;
import Model.Articolo;
import Model.Manager;
import Model.composite.IProdotto;
import View.DettagliPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RifornimentoListener implements ActionListener {
    private MainFrame frame;
    private JTextField quantitaFIeld;
    private IProdotto prodotto;
    private Manager manager;
    public RifornimentoListener(MainFrame frame, JTextField quantitaFIeld, IProdotto prodotto, Manager manager) {
        this.frame = frame;
        this.quantitaFIeld = quantitaFIeld;
        this.prodotto = prodotto;
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Integer.parseInt(quantitaFIeld.getText());
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
            return;
        }

        if (Integer.parseInt(quantitaFIeld.getText()) < 0){
            JOptionPane.showMessageDialog(this.frame, "Verificare i valori inseriti");
            return;
        }

        //Imposto la nuova quantità
        prodotto.setQuantita(Integer.parseInt(quantitaFIeld.getText()));

        //Carico il punto vendita del manager
        PuntoVenditaResult puntoVenditaResult = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByManager(manager);

        //Carico il magazzino da rifornire
        MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByPuntoVendita(puntoVenditaResult.getPuntiVendita().get(0));

        //Aggiorno la quantità nel magazzino
        ArticoloResult articoloResult = ArticoloBusiness.getInstance().updateProdottoInMagazzino(prodotto, magazzinoResult.getMagazzini().get(0));

        //Mostro il messaggio di result
        JOptionPane.showMessageDialog(this.frame, articoloResult.getMessage());

        //Se il rifornimento è andato a buon fine, aggiorno il panel per mostrare la nuova quantità e svuotare il JTextField
        if(articoloResult.getResult().equals(ArticoloResult.Result.UPDATE_OK)){
            this.frame.mostraPannelloAttuale(new DettagliPanel(frame, (Articolo) prodotto, puntoVenditaResult.getPuntiVendita().get(0)));
        }
    }
}
