package View;

import javax.swing.*;

public class MainFrame extends JFrame{

    private JPanel panelAttuale = new JPanel();

    public MainFrame(){
        super("MyShop!");
        initialize();
    }

    public void initialize(){

        this.setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mostraPannelloAttuale(new CatalogoPanel(this));
        this.setVisible(true);
    }

    public void mostraPannelloAttuale(JPanel panel) {
        remove(panelAttuale);
        panelAttuale = panel;
        add(panelAttuale);
        revalidate();
        repaint();
    }

}
