package view.components;

import javax.swing.*;
import java.awt.*;

public class ListCellRenderer extends JLabel implements javax.swing.ListCellRenderer {
    private int rowIndex;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        rowIndex = index;

        String val = value.toString();

        setText(val);

        String[] array = val.split(" | ");
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].replace(" | ", "");
        }

        int state = 1;
        //int state = Integer.parseInt(array[3]);
        switch (state){
            case 0:  setBackground(Color.white);
                break;
            case 1: setBackground(new Color(179, 239, 253));
                break;
            case 2: setBackground(new Color(179, 253, 179));
            default: setBackground(Color.gray);
        }

        setBackground(Color.white);
        if (isSelected) {
            setForeground(Color.black);
        } else {
            setForeground(Color.gray);
        }

        return this;
    }
}
