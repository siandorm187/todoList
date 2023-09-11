package model;

public class Task {
    private int id;
    private String name;
    private String description;
    private int state;
    private String date;

    public Task(){

    }

    public Task(int id, String name, String description, int state, String date){
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getState() {
        return state;
    }

    public String getDate() {
        return date;
    }
}
