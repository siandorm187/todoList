package view;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class TaskView {
    public int frameWidth = 1024;
    public int frameHeight = 768;

    private int taskId = -1;
    private int listItem = -1;


    private DefaultListModel<String> tasksList = new DefaultListModel<>();

    private DefaultListModel<String> emptyList = new DefaultListModel<>();
    private JFrame frame = new JFrame("Task Manager");
    public JPanel listPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel label = new JLabel("Seznam úkolů:");
    public JButton addButton = new JButton();
    public JButton deleteButton = new JButton();
    public JButton chooseButton = new JButton();
    private JList list = new JList(tasksList);
    public void printTasksList(HashMap<Integer, Task> tasks) {
        tasks.forEach((id, item) -> {
            tasksList.addElement(item.getId() + " | " + item.getName() + " | " + item.getDescription() + " | " + item.getState() + " | " + item.getDate());
        });

        listPanel.setLayout(null);
        buttonPanel.setLayout(null);

        listPanel.setBounds(100, 30, this.frameWidth - (this.frameWidth / 3), 500);
        buttonPanel.setBounds(100, 500, 700, 200);

        addButton.setText("Přidat úkol");
        deleteButton.setText("Odebrat úkol");
        chooseButton.setText("Vybrat úkol");
        addButton.setBounds(200, 600, 150, 70);
        deleteButton.setBounds(400, 600, 150, 70);
        chooseButton.setBounds(600, 600, 150, 70);
        addButton.setBackground(new Color(39, 40, 41));
        deleteButton.setBackground(new Color(39, 40, 41));
        chooseButton.setBackground(new Color(39, 40, 41));
        addButton.setForeground(Color.white);
        deleteButton.setForeground(Color.white);
        chooseButton.setForeground(Color.white);
        addButton.setBorderPainted(false);
        deleteButton.setBorderPainted(false);
        chooseButton.setBorderPainted(false);
        chooseButton.setEnabled(false);
        deleteButton.setEnabled(false);

        list.setFixedCellHeight(30);
        list.setBounds(50, 50, listPanel.getWidth(), listPanel.getHeight());

        /*list.addListSelectionListener(e -> {
            int id = Integer.parseInt(list.getSelectedValue().toString().split(" | ")[0]);

            setTaskId(id);
            setListTaskId(e.getLastIndex());

            System.out.println(id);
            System.out.println(e.getFirstIndex());
            chooseButton.setEnabled(true);
            deleteButton.setEnabled(true);
        });*/

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    JList target = (JList) e.getSource();
                    int index = target.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Object item = target.getModel().getElementAt(index);

                        //int id = Integer.parseInt(item.toString().split(" | ")[0]);
                        //String task = tasksList.get(index).split(" | ")[0];

                        /*setTaskId(id);*/
                        setListTaskId(index);

                        //setTaskId(Integer.parseInt(task));

                        System.out.println(getTaskId());

                        //System.out.println(index);

                        chooseButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                    }
                }
            }
        });

        /*list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String item = list.getSelectedValue().toString();
                    int id = Integer.parseInt(item.split(" | ")[0]);

                    System.out.println(id);
                    System.out.println(e.getLastIndex());
                    setTaskId(e.getLastIndex());

                    chooseButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                }
            }
        });*/


        listPanel.add(label);
        listPanel.add(list);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(chooseButton);

        frame.add(listPanel);
        frame.add(buttonPanel);

        frame.setBackground(new Color(217,217,217));
        frame.setSize(this.frameWidth, this.frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public int getTaskId(){
        return taskId;
    }

    public int getListTaskId(){
        return listItem;
    }

    public void setTaskId(int id){
        this.taskId = id;
    }

    public void setListTaskId(int item){
        this.listItem = item;
    }

    public DefaultListModel<String> getTasks(){
        return this.tasksList;
    }
    /*public void test(Function<Integer, Integer> function){
        if(taskId != 0){
            function.apply(taskId);
        }
    }*/

    /*public void changeBtnAction(Runnable function){
        this.chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                function.run();
            }
        });
    }*/
}
