package View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JTableButtonRenderer implements TableCellRenderer {
    private JButton button;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.button = (JButton) value;
        if (isSelected) {
            this.button.setForeground(table.getSelectionForeground());
            this.button.setBackground(table.getSelectionBackground());
        } else {
            this.button.setForeground(table.getForeground());
            this.button.setBackground(UIManager.getColor("Button.background"));
        }
        return this.button;
    }
}
