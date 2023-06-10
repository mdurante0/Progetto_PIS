package View;

import javax.swing.*;

public class MainFrame extends JFrame{

    private JPanel panelAttuale = new JPanel();

    public MainFrame(String title){
        super(title);
        initialize();
    }

    public void initialize(){

        this.setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mostraPannelloAttuale(new LoginPanel(this));
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
