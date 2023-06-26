package View.Listener;

import Business.ListaAcquistoBusiness;
import Business.MagazzinoBusiness;
import Business.Results.ListaAcquistoResult;
import Business.Results.MagazzinoResult;
import Model.Articolo;
import Model.ListaAcquisto;
import Model.Magazzino;
import Model.PuntoVendita;
import Model.composite.IProdotto;
import View.MainFrame;
import View.MostraListeAcquistoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetPagataListener implements ActionListener {
    private MainFrame frame;
    private ListaAcquisto listaAcquisto;
    private JButton pagataButton;

    public SetPagataListener(MainFrame frame, ListaAcquisto listaAcquisto, JButton pagataButton) {
        this.frame = frame;
        this.listaAcquisto = listaAcquisto;
        this.pagataButton = pagataButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(frame, "Confermare la modifica?", "Confermi?", JOptionPane.YES_NO_OPTION);
        if (confirmed == 1)
            return;

        listaAcquisto.setPagata(pagataButton.getText().equals("Non Acquistata"));

        ListaAcquistoResult listaAcquistoResult = ListaAcquistoBusiness.getInstance().updateListaAcquisto(listaAcquisto);
        if (listaAcquistoResult.getResult().equals(ListaAcquistoResult.Result.UPDATE_OK)){

            PuntoVendita puntoVendita = listaAcquisto.getCliente().getPuntoVenditaDiRegistrazione();
            MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().caricaMagazzinoByPuntoVendita(puntoVendita);
            if (magazzinoResult.getResult().equals(MagazzinoResult.Result.MAGAZZINI_CARICATI)){
                Magazzino magazzino = magazzinoResult.getMagazzini().get(0);
                for (int i = 0; i < magazzino.getProdotti().size(); i++) {
                    IProdotto prodotto = magazzino.getProdotti().get(i);
                    for (int j = 0; j < listaAcquisto.getArticoli().size(); j++) {
                        Articolo articoloLista = listaAcquisto.getArticoli().get(j);

                        if (prodotto.getName().equals(articoloLista.getName())) {
                            int disponibilitaRestante;

                            if (listaAcquisto.isPagata())
                                disponibilitaRestante = prodotto.getQuantita() - articoloLista.getQuantita();
                            else disponibilitaRestante = prodotto.getQuantita() + articoloLista.getQuantita();

                            magazzino.getProdotti().get(i).setQuantita(disponibilitaRestante);
                        }
                    }
                }
                MagazzinoBusiness.getInstance().updateMagazzino(magazzino);
            }

            frame.mostraPannelloAttuale(new MostraListeAcquistoPanel(frame));
        }
        else listaAcquisto.setPagata(!pagataButton.getText().equals("Non Acquistata"));
        JOptionPane.showMessageDialog(frame, listaAcquistoResult.getMessage());
    }
}
