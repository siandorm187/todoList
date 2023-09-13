package controller;
import model.Task;
import view.TaskView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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

            int listTaskId = view.getTaskId();

            this.view.chooseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    int id =  Integer.parseInt(view.getTasks().get(view.getListTaskId()).split(" | ")[0]);

                    Task task = tasks.get(id);

                    System.out.println(/*task.getName() + */" id: "+ view.getListTaskId() + " a " + id);
                    //System.out.println(view.getListTaskId());

                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();

                    String dateTime = date + " " + time.getHour()+":"+time.getMinute()+":"+time.getSecond();

                    System.out.println(dateTime + " zmena u " + listTaskId);

                    editTask(fileName, id, "TEST BRATROMILE", task.getDescription(), 1, task.getDate());
                }
            });

            this.view.deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeTask(fileName, listTaskId);
                }
            });

            this.view.addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addTask(fileName, "", "", 0, "");
                }
            });
        }
        catch(FileNotFoundException e){
            System.out.println("Unable to find the file.");
            e.printStackTrace();
        }
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


        this.tasks.forEach((t1ID, t1) -> {
            this.tasks.forEach((t2ID, t2) -> {
                if(t1.getId() != t2.getId()){
                    if(t1.getId() > t2.getId()){
                        if(maxIndex[0] == -1){
                            maxIndex[0] = t1.getId();
                        }
                        else{
                            if(t1.getId() > maxIndex[0]){
                                maxIndex[0] = t1.getId();
                            }
                        }
                    }
                }
            });
        });

        task.setId(maxIndex[0]);

        this.tasks.put(task.getId(), task);

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

    /*public void resetView(){
        for(Task item : tasks){
            view..addElement(item.getId() + " | "+item.getName()+" | "+item.getDescription()+" | "+item.getState()+" | "+item.getDate());
        }
    }*/
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
