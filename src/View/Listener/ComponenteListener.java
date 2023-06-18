package View.Listener;

import Business.CatalogoBusiness;
import Business.Results.CatalogoResult;
import Model.composite.IProdotto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ComponenteListener implements ActionListener {
    private JPanel panel;
    private static int componentsCounter = 2;
    private JButton aggiungiComponenteButton;
    private ArrayList<JComboBox<String>> componentiBoxes;
    private ArrayList<JTextField> quantitaComponentiFields;
    public final static String AGGIUNGI = "aggiungi";
    public final static String RIMUOVI = "rimuovi";

    public ComponenteListener(JPanel panel, JButton aggiungiComponenteButton, ArrayList<JComboBox<String>> componentiBoxes, ArrayList<JTextField> quantitaComponentiFields) {
        this.panel = panel;
        this.aggiungiComponenteButton = aggiungiComponenteButton;
        this.componentiBoxes = componentiBoxes;
        this.quantitaComponentiFields = quantitaComponentiFields;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(AGGIUNGI)) {
            componentsCounter++;
            JLabel componenteN = new JLabel("  Componente " + componentsCounter + ":");
            Font bodyFont = new Font(Font.DIALOG, Font.ITALIC, 22);
            componenteN.setFont(bodyFont);
            JComboBox<String> componenteNBox = new JComboBox<>();
            CatalogoResult catalogoResult = CatalogoBusiness.getInstance().caricaCatalogoProdotti();
            if (catalogoResult.getListaProdotti() != null) {
                Iterator<IProdotto> iterator = catalogoResult.getListaProdotti().iterator();
                String[] nomiProdotti = new String[catalogoResult.getListaProdotti().size()];
                for (int i = 0; i < catalogoResult.getListaProdotti().size(); i++) {
                    nomiProdotti[i] = iterator.next().getName();
                }
                componenteNBox = new JComboBox<>(nomiProdotti);
                componenteNBox.setFocusable(false);
                componenteNBox.setFont(bodyFont);
            }
            componentiBoxes.add(componenteNBox);

            JLabel quantitaN = new JLabel("  Quantità componente " + componentsCounter + ":");
            quantitaN.setFont(bodyFont);
            JTextField quantitaNField = new JTextField(20);
            quantitaNField.setFont(bodyFont);
            quantitaComponentiFields.add(quantitaNField);

            JButton rimuoviComponenteButton = new JButton("Rimuovi componente");
            rimuoviComponenteButton.setFont(bodyFont);
            rimuoviComponenteButton.setActionCommand(RIMUOVI);
            rimuoviComponenteButton.addActionListener(new ComponenteListener(this.panel, aggiungiComponenteButton, componentiBoxes, quantitaComponentiFields));

            panel.remove(panel.getComponentCount() - 2);
            panel.remove(aggiungiComponenteButton);

            panel.add(componenteN);
            panel.add(componenteNBox);
            panel.add(quantitaN);
            panel.add(quantitaNField);
            panel.add(new JLabel());
            panel.add(new JLabel());
            panel.add(rimuoviComponenteButton);
            panel.add(aggiungiComponenteButton);

        } else if (e.getActionCommand().equals(RIMUOVI) && componentsCounter > 2) {
            componentsCounter--;
            componentiBoxes.remove(componentiBoxes.size() - 1);
            quantitaComponentiFields.remove(quantitaComponentiFields.size() -1);

            panel.remove(panel.getComponentCount() - 3); //label vuota
            panel.remove(panel.getComponentCount() - 3); //label vuota
            panel.remove(panel.getComponentCount() - 3); //quantitàNField
            panel.remove(panel.getComponentCount() - 3); //quantitàNLabel
            panel.remove(panel.getComponentCount() - 3); //componenteNBox
            panel.remove(panel.getComponentCount() - 3); //componenteNLabel

            if(componentsCounter == 2) {
                panel.remove(panel.getComponentCount() - 2); //rimuoviComponenteButton
                panel.remove(aggiungiComponenteButton);
                panel.add(new JLabel());
                panel.add(aggiungiComponenteButton);

            }
        }

        panel.repaint();
        panel.revalidate();

    }
}
