package View.Listener;

import Business.ListaAcquistoBusiness;
import Business.MagazzinoBusiness;
import Business.MailBusiness;
import Business.PrenotazioneBusiness;
import Business.Results.MagazzinoResult;
import Business.Results.MailResult;
import Business.Results.PrenotazioneResult;
import Model.*;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import View.MainFrame;
import View.MostraListeAcquistoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Date;

public class AcquistoListener implements ActionListener {
    private MainFrame frame;
    private ListaAcquisto listaAcquisto;

    public AcquistoListener(MainFrame frame, ListaAcquisto listaAcquisto) {
        this.frame = frame;
        this.listaAcquisto = listaAcquisto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PuntoVendita puntoVendita = listaAcquisto.getCliente().getPuntoVenditaDiRegistrazione();
        MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByPuntoVendita(puntoVendita);
        if (!magazzinoResult.getResult().equals(MagazzinoResult.Result.MAGAZZINI_CARICATI)){
            JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());
            return;
        }
        Magazzino magazzino = magazzinoResult.getMagazzini().get(0);

        for (int i = 0; i < listaAcquisto.getArticoli().size(); i++) {
            Articolo articoloLista = listaAcquisto.getArticoli().get(i);

            for (int j = 0; j < magazzino.getProdotti().size(); j++) {
                IProdotto prodotto = magazzino.getProdotti().get(j);

                if (articoloLista.getName().equals(prodotto.getName())) {
                    ((Prodotto) listaAcquisto.getArticoli().get(i)).setCollocazione(prodotto.getCollocazione());

                    if (articoloLista.getQuantita() > prodotto.getQuantita()) {
                        int quantitaRestanti = articoloLista.getQuantita() - prodotto.getQuantita();
                        int confirmed = JOptionPane.showConfirmDialog(this.frame, "La quantità richiesta per " + articoloLista.getName() + " è superiore alla disponibilità presente in magazzino. Vuole prenotare i restanti " + quantitaRestanti + "?", "Prenotare?", JOptionPane.YES_NO_OPTION);
                        if (prodotto.getQuantita() == 0) {
                            listaAcquisto.remove(articoloLista);
                            ListaAcquistoBusiness.getInstance().removeArticoloFromListaAcquisto(listaAcquisto, articoloLista.getName());
                        } else listaAcquisto.getArticoli().get(i).setQuantita(prodotto.getQuantita());

                        if (confirmed == 0) {
                            prodotto.setQuantita(quantitaRestanti);
                            Prenotazione prenotazione = new Prenotazione(prodotto, Date.from(Instant.now()), listaAcquisto.getCliente().getIdUtente());

                            PrenotazioneResult prenotazioneResult = PrenotazioneBusiness.getInstance().addPrenotazione(prenotazione);
                            JOptionPane.showMessageDialog(frame, prenotazioneResult.getMessage());
                        }
                    }
                }
            }
        }
        ListaAcquistoBusiness.getInstance().updateListaAcquisto(listaAcquisto);

        MailResult mailResult = MailBusiness.getInstance().invioListaAcquisto(listaAcquisto);
        JOptionPane.showMessageDialog(this.frame, mailResult.getMessage());
        if(mailResult.getResult().equals(MailResult.Result.INVIO_OK))
            this.frame.mostraPannelloAttuale(new MostraListeAcquistoPanel(this.frame));
    }
}
