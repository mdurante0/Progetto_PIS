package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private JPanel panelAttuale;

    public MainFrame(){
        super("MyShop");

        this.setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        panelAttuale = new LoginPanel(this);
        c.add(panelAttuale);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    public void mostraPannelloAttuale(JPanel panel) {
        remove(panelAttuale);
        add(panel);
        panelAttuale = panel;
        revalidate();
        repaint();
    }

}
