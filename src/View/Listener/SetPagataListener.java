package View.Listener;

import Business.ListaAcquistoBusiness;
import Business.Results.ListaAcquistoResult;
import Model.ListaAcquisto;
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
            frame.mostraPannelloAttuale(new MostraListeAcquistoPanel(frame));
        }
        else listaAcquisto.setPagata(!pagataButton.getText().equals("Non Acquistata"));
        JOptionPane.showMessageDialog(frame, listaAcquistoResult.getMessage());
    }
}
