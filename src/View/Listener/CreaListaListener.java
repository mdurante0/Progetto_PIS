package View.Listener;

import Business.ListaAcquistoBusiness;
import Business.Results.ListaAcquistoResult;
import Business.SessionManager;
import Model.Articolo;
import Model.Cliente;
import Model.ListaAcquisto;
import Model.PuntoVendita;
import View.DettagliPanel;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CreaListaListener implements ActionListener {
    private MainFrame frame;
    private JTextField nomeListaField;
    private Articolo articolo;
    private PuntoVendita puntoVendita;

    public CreaListaListener(MainFrame frame, JTextField nomeListaField, Articolo articolo, PuntoVendita puntoVendita) {
        this.frame = frame;
        this.nomeListaField = nomeListaField;
        this.articolo = articolo;
        this.puntoVendita = puntoVendita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cliente c = (Cliente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        ListaAcquisto listaAcquisto = new ListaAcquisto(c.getIdUtente(), false, nomeListaField.getText(), new Date());
        listaAcquisto.add(articolo);

        ListaAcquistoResult listaAcquistoResult = ListaAcquistoBusiness.getInstance().addListaAcquisto(listaAcquisto);
        JOptionPane.showMessageDialog(this.frame, listaAcquistoResult.getMessage());
        if(listaAcquistoResult.getResult().equals(ListaAcquistoResult.Result.ADD_OK))
            this.frame.mostraPannelloAttuale(new DettagliPanel(this.frame, articolo, puntoVendita));
    }
}
