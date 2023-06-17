package View.Listener;

import Business.CatalogoBusiness;
import Business.Results.CatalogoResult;
import Model.composite.IProdotto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class AggiungiComponenteListener implements ActionListener {
    private JPanel panel;
    private int componentsCounter;
    private JButton aggiungiComponenteButton;
    public AggiungiComponenteListener(JPanel panel, int componentsCounter, JButton aggiungiComponenteButton) {
        this.panel = panel;
        this.componentsCounter = componentsCounter;
        this.aggiungiComponenteButton = aggiungiComponenteButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        componentsCounter++;
        JLabel componenteN = new JLabel("  Componente " + componentsCounter + ":");
        Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 22);
        componenteN.setFont(bodyFont);
        JComboBox<String> componenteNBox = new JComboBox<>();
        CatalogoResult catalogoResult = CatalogoBusiness.getInstance().caricaCatalogoProdotti();
        if(catalogoResult.getListaProdotti() != null) {
            Iterator<IProdotto> iterator = catalogoResult.getListaProdotti().iterator();
            String[] nomiProdotti = new String[catalogoResult.getListaProdotti().size()];
            for (int i = 0; i < catalogoResult.getListaProdotti().size(); i++) {
                nomiProdotti[i] = iterator.next().getName();
            }
            componenteNBox = new JComboBox<>(nomiProdotti);
            componenteNBox.setFocusable(false);
            componenteNBox.setFont(bodyFont);
        }

        JLabel quantitaN = new JLabel("  Quantità componente " + componentsCounter + ":");
        quantitaN.setFont(bodyFont);
        JTextField quantitaNField = new JTextField(20);
        quantitaNField.setFont(bodyFont);

        panel.remove(panel.getComponentCount()-2);
        panel.remove(aggiungiComponenteButton);

        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(componenteN);
        panel.add(componenteNBox);
        panel.add(quantitaN);
        panel.add(quantitaNField);
        panel.add(new JLabel());
        panel.add(aggiungiComponenteButton);
        panel.repaint();
        panel.revalidate();

    }
}
