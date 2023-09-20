package view;

import model.Task;

import javax.swing.*;
import java.awt.*;

public class DetailView {
    //todo: pro jednotlivé tasky nové okno při otevření, kde by byl list podrobností - tečky, pomlčky, atd
    private JFrame frame = new JFrame();

    public void printTask(String task){
        List list = new List();

        frame.setTitle(task);
        frame.setBackground(new Color(217,217,217));
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
