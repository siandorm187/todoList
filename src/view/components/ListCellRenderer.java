package view.components;

import lib.Colors;

import javax.swing.*;
import java.awt.*;

public class ListCellRenderer extends DefaultListCellRenderer {
    private int rowIndex;
    private Colors colors = new Colors();

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //todo: dostat state ball pomoci tohoto, ne staticky do stringu pres html jak predtim
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (isSelected) {
            c.setForeground(colors.colorComponent);
            c.setBackground(colors.colorMain);
        }

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
                break;
            default: setBackground(Color.gray);
        }

        return c;
    }
}
