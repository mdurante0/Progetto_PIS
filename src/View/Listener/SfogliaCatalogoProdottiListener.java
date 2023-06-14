package View.Listener;

import Business.PuntoVenditaBusiness;
import Business.Results.PuntoVenditaResult;
import Business.SessionManager;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;
import Model.Utente;
import View.CatalogoProdottiPanel;
import View.MainFrame;
import View.MenuPuntiVenditaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SfogliaCatalogoProdottiListener implements ActionListener {

    private MainFrame frame;

    public SfogliaCatalogoProdottiListener(MainFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Ricavo l'utente loggato
        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        //Se è un guest o un amministratore gli permetto di accedere al punto vendita desiderato
        if(u == null || u instanceof Amministratore)
            frame.mostraPannelloAttuale(new MenuPuntiVenditaPanel(this.frame));

        //Se è un manager
        else if(u instanceof Manager m) {
            PuntoVenditaResult result = PuntoVenditaBusiness.getInstance().caricaPuntoVenditaByManager(m);

            //Se il manager indicato non è ancora stato assegnato a nessun punto vendita gli permetto di accedere al punto vendita desiderato
            if (result.getPuntiVendita().isEmpty())
                frame.mostraPannelloAttuale(new MenuPuntiVenditaPanel(this.frame));

            //Altrimenti lo faccio accedere al punto vendita che gli è stato assegnato
            else frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, result.getPuntiVendita().get(0).getNome()));

        //Infine se è un cliente lo faccio accedere al suo punto vendita di registrazione
        } else if (u instanceof Cliente c) {
            frame.mostraPannelloAttuale(new CatalogoProdottiPanel(this.frame, c.getPuntoVenditaDiRegistrazione().getNome()));

        }
    }
}
