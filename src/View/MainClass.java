package View;

import java.awt.*;

public class MainClass {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame("MyShop!");
            }
        });

    }
}
