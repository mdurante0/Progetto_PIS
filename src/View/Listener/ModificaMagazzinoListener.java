package View.Listener;

import Business.MagazzinoBusiness;
import Business.Results.MagazzinoResult;
import Model.Magazzino;
import View.MainFrame;
import View.MenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificaMagazzinoListener implements ActionListener {
    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JTextField indirizzoField;
    private JTextField quantitaCorsieField;
    private JTextField quantitaScaffaliField;
    private Magazzino magazzino;
    public ModificaMagazzinoListener(MainFrame frame, JTextField indirizzoField, JTextField quantitaCorsieField, JTextField quantitaScaffaliField, Magazzino m) {
        this.frame = frame;
        this.indirizzoField = indirizzoField;
        this.quantitaCorsieField = quantitaCorsieField;
        this.quantitaScaffaliField = quantitaScaffaliField;
        magazzino = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        magazzino.setIndirizzo(indirizzoField.getText());
        magazzino.setQuantitaCorsie(Integer.parseInt(quantitaCorsieField.getText()));
        magazzino.setQuantitaScaffali(Integer.parseInt(quantitaScaffaliField.getText()));

        if(!magazzino.getIndirizzo().isEmpty() && magazzino.getQuantitaCorsie() != 0 && magazzino.getQuantitaScaffali() != 0){
            MagazzinoResult magazzinoResult = MagazzinoBusiness.getInstance().updateMagazzino(magazzino);
            if(magazzinoResult.getResult() == MagazzinoResult.Result.UPDATE_OK)
                this.frame.mostraPannelloAttuale(new MenuPanel(this.frame));
            JOptionPane.showMessageDialog(this.frame, magazzinoResult.getMessage());
        }else
            JOptionPane.showMessageDialog(this.frame, "Attenzione, il magazzino deve avere i seguinti campi: indirizzo, numero totale di corsie e numero totale di scaffali!");
    }
}
