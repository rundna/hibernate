package com.kodilla.hibernate.taskslist;

import com.kodilla.hibernate.task.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TASKSLIST")
public class TasksList {
    private int id;
    private String listName;
    private String description;
    private List<Task> tasks = new ArrayList<>();

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique=true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="LISTNAME")
    public String getListName() {
        return listName;
    }

    private void setListName(String listName) {
        this.listName = listName;
    }
    @Column(name="DESCRIPTION")
    private String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }
    public TasksList(){}

    public TasksList(String listName, String description) {
        this.id = id;
        this.listName = listName;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TasksList{" +
                "listName='" + listName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @OneToMany(
            targetEntity = Task.class,
            mappedBy="tasksList",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
