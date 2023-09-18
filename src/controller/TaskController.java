package controller;
import model.Task;
import view.TaskView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class TaskController {
    private Task task;
    private TaskView view;
    private HashMap<Integer,Task> tasks = new HashMap<>();

    private String fileName = "data.csv";

    public TaskController(Task task, TaskView view){
        this.task = task;
        this.view = view;
    }

    public void getDataFromFile(String fileName){
        try{
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                Task task = new Task();

                String data = sc.nextLine();
                String[] taskInfo = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                task.setId(Integer.parseInt(taskInfo[0]));
                task.setName(taskInfo[1]);
                task.setDescription(taskInfo[2]);
                task.setState(Integer.parseInt(taskInfo[3]));
                task.setDate(taskInfo[4]);

                this.tasks.put(task.getId(),task);
            }

            //todo: fixnout hento
            final int[] id = new int[1];

            this.view.chooseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    id[0] =  Integer.parseInt(view.getTasks().get(view.getListTaskId()).split(" | ")[0]);

                    Task task = tasks.get(id[0]);

                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();

                    String dateTime = date + " " + time.getHour()+":"+time.getMinute()+":"+time.getSecond();

                    System.out.println("id je "+ id[0]);

                    editTask(fileName, id[0], task.getName(), "HFGHFGHFDGHFDGFDFGH", 1, dateTime);

                    view.editTaskPanel.setVisible(true);

                    reload();
                }
            });

            this.view.deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    id[0] =  Integer.parseInt(view.getTasks().get(view.getListTaskId()).split(" | ")[0]);
                    System.out.println("id je "+ id[0]);
                    removeTask(fileName, id[0]);

                    reload();
                }
            });

            this.view.addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.taskNameIn.setVisible(true);
                    view.taskDescIn.setVisible(true);
                    addTask(fileName, "a", "a", 0, "a");
                    view.addTaskPanel.setVisible(true);

                    reload();
                }
            });

            this.view.refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //view.reloadFrame();

                    reload();
                }
            });
        }
        catch(FileNotFoundException e){
            System.out.println("Unable to find the file.");
            e.printStackTrace();
        }
    }

    public void reload(){
        DefaultListModel list = new DefaultListModel<Task>();
        tasks.forEach((k, v) -> {
            System.out.println(v.getName());
            list.addElement(v);
        });
        view.updateList(tasks);
        //todo: ulozit hodnoty do listu
        view.refreshButton.setText("Refreshnuto");
        view.refreshButton.setBackground(Color.red);
        view.reloadFrame();
    }

    public void addTask(String fileName, Task task){
        this.tasks.put(task.getId(),task);

        this.rewriteFile(fileName);
    }

    public void addTask(String fileName, String taskName, String taskDesc, int taskState, String taskDate){
        Task task = new Task();

        task.setName(taskName);
        task.setDescription(taskDesc);
        task.setState(taskState);
        task.setDate(taskDate);

        final int[] maxIndex = {-1};

        int id = Collections.max(this.tasks.keySet())+1;
        task.setId(id);

        System.out.println("MAXIMALNI INDEX MORE" + id);

        this.tasks.put(id, task);
        this.rewriteFile(fileName);
    }

    public void removeTask(String fileName, int taskId){
        Task task = this.tasks.get(taskId);

        if(!this.tasks.isEmpty()){
            this.tasks.remove(task.getId());
        }

        this.rewriteFile(fileName);
    }

    public void editTask(String fileName, int taskId, String taskName, String taskDesc, int taskState, String taskDate) {
        Task task = new Task();
        this.tasks.remove(taskId);

        task.setName(taskName);
        task.setDescription(taskDesc);
        task.setState(taskState);
        task.setDate(taskDate);
        task.setId(taskId);

        this.tasks.put(task.getId(), task);

        this.rewriteFile(fileName);
    }

    public void rewriteFile(String fileName){
        try{
            File file = new File(fileName);
            FileWriter writer = new FileWriter(fileName, false);

            if(file.exists()){

                this.tasks.forEach((tId, t) -> {
                    String id = String.valueOf(t.getId());
                    try {
                        writer.write(id+','+t.getName()+','+t.getDescription()+','+t.getState()+','+t.getDate()+'\n');
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Writing : " + t.getName() + ":" + t.getId());
                });

                writer.close();
            }
            else{
                System.out.println("File not found.");
            }

        }
        catch(FileNotFoundException e){
            System.out.println("Unable to find the file.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateView(){

        //view.printTasksList();
        getDataFromFile("./public/data.csv");

        /*this.tasks.forEach(t -> {
            System.out.println(t.getName());
        });*/

        if(!this.tasks.isEmpty()){
            view.printTasksList(this.tasks);
            if(view.getTaskId() != 0){
                System.out.println("Vybrali jste task");
            }
        }
    }
}
