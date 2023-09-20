package view;
import model.Task;
import view.components.ListCellRenderer;

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
    public JButton delYesButton = new JButton();
    public JButton delNoButton = new JButton();
    public JButton refreshButton = new JButton();
    public JPanel addTaskPanel = new JPanel();
    public JPanel deleteTaskPanel = new JPanel();
    public JTextArea taskNameIn = new JTextArea();
    public JTextArea taskDescIn = new JTextArea();
    public JButton finalAddButton = new JButton();
    public JLabel lblInfo = new JLabel("");
    public JLabel lblAddIn1 = new JLabel("");
    public JLabel lblAddIn2 = new JLabel("");
    public JLabel lblDelete = new JLabel("Opravdu chcete vymazat tento úkol?");
    private JList list = new JList(tasksList);

    private DetailView detail = new DetailView();
    public void printTasksList(HashMap<Integer, Task> tasks) {

        list.setModel(parseListData(tasks));

        int width = this.frameWidth - (this.frameWidth / 4);

        listPanel.setLayout(null);
        listPanel.setBounds(100, 30, width, 400);

        lblInfo.setBounds(150, 420, width, 50);

        buttonPanel.setLayout(null);
        buttonPanel.setBounds(100, 800, width, 200);

        addTaskPanel.setLayout(null);
        addTaskPanel.setBounds(100, 450, width, 100);
        addTaskPanel.setBackground(Color.white);

        deleteTaskPanel.setBackground(Color.white);
        deleteTaskPanel.setLayout(null);
        deleteTaskPanel.setBounds(100, 450, width, 100);

        addButton.setText("Přidat úkol");
        addButton.setBounds(200, 600, 150, 70);
        addButton.setBackground(new Color(39, 40, 41));
        addButton.setForeground(Color.white);
        addButton.setBorderPainted(false);

        lblAddIn1.setText("Název: ");
        lblAddIn1.setBounds(50, 25, 100, 30);

        lblAddIn2.setText("Podrobnosti: ");
        lblAddIn2.setBounds(250, 25, 100, 30);

        taskNameIn.setForeground(Color.gray);
        taskNameIn.setBounds(100, 30, 100, 30);
        taskNameIn.setToolTipText("Název: ");
        taskNameIn.setRows(1);
        taskNameIn.setBackground(new Color(217,217,217));

        taskDescIn.setForeground(Color.gray);
        taskDescIn.setBounds(340, 30, 200, 50);
        taskDescIn.setRows(3);
        taskDescIn.setBackground(new Color(217,217,217));
        taskDescIn.setLineWrap(true);
        taskDescIn.setToolTipText("Podrobnosti: ");

        finalAddButton.setText("Přidat úkol");
        finalAddButton.setBounds(600, 25, 100, 50);
        finalAddButton.setBackground(new Color(107, 187, 107));
        finalAddButton.setForeground(Color.white);
        finalAddButton.setBorderPainted(false);
        finalAddButton.setEnabled(true);

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
        refreshButton.setVisible(false);



        delYesButton.setText("Ano");
        delYesButton.setBounds(400, 25, 100, 50);
        delYesButton.setBackground(Color.red);
        delYesButton.setForeground(Color.white);
        delYesButton.setBorderPainted(false);
        delYesButton.setEnabled(true);

        delNoButton.setText("Ne");
        delNoButton.setBounds(600, 25, 100, 50);
        delNoButton.setBackground(Color.green);
        delNoButton.setForeground(Color.white);
        delNoButton.setBorderPainted(false);
        delNoButton.setEnabled(true);

        lblDelete.setBounds(50, 25, 300,50);

        list.setFixedCellHeight(30);
        list.setBounds(50, 50, listPanel.getWidth(), listPanel.getHeight());

        //todo: custom renderer pro barvy jednotlivych radku
        list.setCellRenderer(new ListCellRenderer());

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList target = (JList) e.getSource();
                int index = target.locationToIndex(e.getPoint());
                if(index >= 0){
                    if(e.getClickCount() == 1){
                        setListTaskId(index);

                        System.out.println(getTaskId());
                        System.out.println(getListTaskId());

                        chooseButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                    }

                    if(e.getClickCount() == 2){
                        detail.printTask(tasksList.get(index));

                    }
                }
            }
        });

        listPanel.add(label);
        listPanel.add(list);
        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(chooseButton);

        addTaskPanel.add(taskNameIn);
        addTaskPanel.add(taskDescIn);
        addTaskPanel.add(finalAddButton);
        addTaskPanel.add(lblAddIn1);
        addTaskPanel.add(lblAddIn2);
        addTaskPanel.setVisible(false);

        deleteTaskPanel.add(lblDelete);
        deleteTaskPanel.add(delNoButton);
        deleteTaskPanel.add(delYesButton);
        deleteTaskPanel.setVisible(false);

        frame.setBackground(new Color(217,217,217));
        frame.setSize(this.frameWidth, this.frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.add(lblInfo);
        frame.add(listPanel);
        frame.add(deleteTaskPanel);
        frame.add(addTaskPanel);
        frame.add(buttonPanel);
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

    public void reloadFrame(){//ListModel tasks){
        System.out.println("Reloaded");
        //list = new JList(tasks);
        frame.revalidate();
        frame.repaint();
    }

    public void updateList(HashMap tasks){
        //list = new JList(tasks);
        list.setModel(parseListData(tasks));
        list.revalidate();
        list.repaint();
    }

    public DefaultListModel parseListData(HashMap<Integer, Task> tasks){
        DefaultListModel<String> taskL = new DefaultListModel<>();

        tasks.forEach((id, item) -> {
            String stateColor = " ";
            switch(item.getState()){
                case 0: stateColor =  "green";
                    break;
                case 1: stateColor = "blue";
                    break;
                default: stateColor = "red";
            }

            boolean validStates = item.getState() == 0 || item.getState() == 1;

            String htmlStart = "<html><font>";
            String htmlEnd = "</font><font color='"+stateColor+"'>⚫</font></html>";
            String el =  item.getId() + " | " + item.getName() + " | " + item.getDescription() + " | do " + item.getDate() + " | ";
            //System.out.println("\u001B[31m"+ "joojoojojojoj" + "\u001B[0m");
            //todo: fixnout string - ted facha barvicka ale nefacha nic jinyho
            //String el = "<html><font>"+item.getId() + " | " + item.getName() + " | " + item.getDescription() + " | do " + item.getDate() + " | </font><font color='"+stateColor+"'>⚫</font></html>";
            String elText = htmlStart + el + htmlEnd;

            if(validStates) {
                taskL.addElement(elText);
                tasksList.addElement(el);
            }
            else System.out.println("Task ID "+ item.getId() + " has invalid state");
        });

        return taskL;
    }
}
