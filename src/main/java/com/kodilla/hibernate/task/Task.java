package com.kodilla.hibernate.task;

import com.kodilla.hibernate.taskslist.TasksList;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@NamedQueries({
        @NamedQuery(
                    name = "Task.retrieveLongTasks",
                    query = "FROM Task WHERE duration > 10"),
        @NamedQuery(
                name = "Task.retrieveShortTasks",
                query = "FROM Task WHERE duration <=10")

})
@NamedNativeQuery(
        name = "Task.retrieveTasksWithEnoughTime",
        query = "SELECT * FROM TASKS"+
                "WHERE DATEDIFF(DATE_ADD(CREATED,INTERVAL DURATION DAY),NOW())>5",
        resultClass = Task.class
)
@Entity
@Table(name="TASKS")
public class Task {
    private int id;
    private String description;
    private Date created;
    private int duration;
    private TaskFinancialDetails taskFinancialDetails;
    private TasksList tasksList;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="TASKS_FINANCIALS_ID")
    public TaskFinancialDetails getTaskFinancialDetails() {
        return taskFinancialDetails;
    }
    @ManyToOne
    @JoinColumn(name="TASKSLIST_ID")
    public TasksList getTasksList() {
        return tasksList;
    }

    public void setTasksList(TasksList tasksList) {
        this.tasksList = tasksList;
    }

    public void setTaskFinancialDetails(TaskFinancialDetails taskFinancialDetails) {
        this.taskFinancialDetails = taskFinancialDetails;
    }

    public Task(String description, int duration) {
        this.description = description;
        this.created = new Date();
        this.duration = duration;
    }
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique=true)
    public int getId() {
        return id;
    }
    @Column(name="DESCRIPITON")
    public String getDescription() {
        return description;
    }
    @NotNull
    @Column(name="CREATED")
    public Date getCreated() {
        return created;
    }
    @Column(name="DURATION")
    public int getDuration() {
        return duration;
    }

    public Task() {
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setCreated(Date created) {
        this.created = created;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }
}