package view;
import model.Task;
import view.components.ListCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.charset.StandardCharsets;
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
    public JButton refreshButton = new JButton();
    public JPanel addTaskPanel = new JPanel();
    public JPanel editTaskPanel = new JPanel();
    public JTextField taskNameIn = new JTextField();
    public JTextField taskDescIn = new JTextField();
    private JList list = new JList(tasksList);
    public void printTasksList(HashMap<Integer, Task> tasks) {

        tasks.forEach((id, item) -> {
            String s = "\uD83D\uDFE2";
            String test = new String(s.getBytes(StandardCharsets.UTF_16), StandardCharsets.UTF_16);

            char stateCh = ' ';
            switch(item.getState()){
                case 0: stateCh = '⚪';
                break;
                case 1: stateCh = '⚫';
                break;
                default: stateCh = '⚠';
            }

            tasksList.addElement(item.getId() + " | " + item.getName() + " | " + item.getDescription() + " | do " + item.getDate() + " | " +stateCh);
        });

        int width = this.frameWidth - (this.frameWidth / 3);

        listPanel.setLayout(null);
        listPanel.setBounds(100, 30, width, 400);

        buttonPanel.setLayout(null);
        buttonPanel.setBounds(100, 800, width, 200);

        addTaskPanel.setLayout(null);
        addTaskPanel.setBounds(100, 450, width, 100);

        editTaskPanel.setLayout(null);
        editTaskPanel.setBounds(100, 450, width-10, 110);

        addButton.setText("Přidat úkol");
        addButton.setBounds(200, 600, 150, 70);
        addButton.setBackground(new Color(39, 40, 41));
        addButton.setForeground(Color.white);
        addButton.setBorderPainted(false);

        taskNameIn.setForeground(Color.gray);
        taskNameIn.setText("Název: ");

        taskDescIn.setForeground(Color.gray);
        taskDescIn.setText("Podrobnosti: ");

        deleteButton.setText("Odebrat úkol");
        deleteButton.setBounds(400, 600, 150, 70);
        deleteButton.setBackground(new Color(39, 40, 41));
        deleteButton.setForeground(Color.white);
        deleteButton.setBorderPainted(false);
        deleteButton.setEnabled(false);

        chooseButton.setText("Vybrat úkol");
        chooseButton.setBounds(600, 600, 150, 70);
        chooseButton.setBackground(new Color(39, 40, 41));
        chooseButton.setForeground(Color.white);
        chooseButton.setBorderPainted(false);
        chooseButton.setEnabled(false);

        refreshButton.setText("R");
        refreshButton.setBounds(50, 80, 50, 50);
        refreshButton.setBackground(new Color(39, 40, 41));
        refreshButton.setForeground(Color.white);
        refreshButton.setBorderPainted(false);

        list.setFixedCellHeight(30);
        list.setBounds(50, 50, listPanel.getWidth(), listPanel.getHeight());

        for(int i = 0; i< list.getModel().getSize(); i++){
            int id = Integer.parseInt(list.getModel().getElementAt(i).toString().split("|")[0]);

            Task t = tasks.get(id);
            //list.getModel()
            //System.out.println(list.getModel().getElementAt(i).toString().split(" | ")[3] + "z "+ list.getModel().getElementAt(i).toString());
        }

        //todo: custom renderer pro barvy jednotlivych radku
        list.setCellRenderer(new ListCellRenderer());

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
        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(chooseButton);

        addTaskPanel.add(taskNameIn);
        addTaskPanel.add(taskDescIn);
        addTaskPanel.setBackground(Color.green);
        editTaskPanel.setBackground(Color.red);

        frame.add(listPanel);
        //frame.add(editTaskPanel);
        frame.add(addTaskPanel);
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

    public void reloadFrame(){
        System.out.println("Reloaded");

        frame.revalidate();
        frame.repaint();
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
