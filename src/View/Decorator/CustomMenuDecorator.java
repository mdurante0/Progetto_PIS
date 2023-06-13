package View.Decorator;

import View.MainFrame;

import javax.swing.*;
import java.util.List;

public abstract class CustomMenuDecorator extends Menu{

    protected MainFrame frame;
    protected Menu menu;

    @Override
    public abstract List<JButton> getPulsanti();

}
