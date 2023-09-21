package view;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class DetailView {
    //todo: pro jednotlivé tasky nové okno při otevření, kde by byl list podrobností - tečky, pomlčky, atd
    private JFrame frame = new JFrame();
    private JPanel panelLbl = new JPanel();
    private JLabel lblName = new JLabel();
    private JLabel lblDesc = new JLabel();
    private JLabel lblDate = new JLabel();

    private JButton btnFinish = new JButton();

    public void printTask(String task){
        String[] splitString = task.split("\\|");

        List<String> textValues = Arrays.asList(splitString);
        textValues.removeIf(value -> value.matches("\\d+"));

        String id = textValues.get(0);
        String name = textValues.get(1);
        String desc = textValues.get(2);
        String dateTime = textValues.get(3);

        panelLbl.setBackground(Color.white);
        panelLbl.setLayout(null);
        panelLbl.setBounds(100, 450, 600, 100);

        lblName.setText(name);
        lblName.setFont(new Font(lblName.getFont().getName(), Font.PLAIN, 30));
        lblName.setBounds(20, 20, 400, 30);
        lblName.setForeground(new Color(38, 166, 211));

        lblDesc.setText("<html><div WIDTH=600>"+desc+"</div></html>");
        lblDesc.setBounds(40, 60, 600, 120);

        lblDate.setText(dateTime);
        lblDate.setFont(new Font(lblDate.getFont().getName(), Font.PLAIN, 30));
        lblDate.setBounds(100, 100, 200, 30);

        btnFinish.setText("Hotovo");
        btnFinish.setBounds(250, 200, 150, 70);
        btnFinish.setBackground(new Color(107, 187, 107));
        btnFinish.setForeground(Color.white);
        btnFinish.setBorderPainted(false);

        panelLbl.add(lblName);
        panelLbl.add(lblDesc);
        panelLbl.add(lblDate);

        frame.add(panelLbl);
        frame.add(btnFinish);

        frame.setTitle(textValues.get(2));
        frame.setBackground(new Color(217,217,217));
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
