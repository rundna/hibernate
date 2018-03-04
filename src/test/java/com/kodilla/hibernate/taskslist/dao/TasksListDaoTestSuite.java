package com.kodilla.hibernate.taskslist.dao;

import com.kodilla.hibernate.task.Task;
import com.kodilla.hibernate.task.TaskFinancialDetails;
import com.kodilla.hibernate.taskslist.TasksList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksListDaoTestSuite {

    @Autowired
    private TasksListDao tasksListDao;
    private static final String TASKNAME = "First Task";

    @Test
    public void testFindByListName() {
        TasksList task = new TasksList(TASKNAME,"My first task");
        tasksListDao.save(task);

        List<TasksList> taskClone = tasksListDao.findByListName("First Task");


        //System.out.println(task.toString());
        Assert.assertEquals(1, taskClone.size());

        //taskListDao.deleteAll();
    }
    @Test
    public void testTaskListDaoSaveWithTasks() {
        //Given
        Task task = new Task("Test: Learn Hibernate", 14);
        Task task2 = new Task("Test: Write some entities", 3);
        TaskFinancialDetails tfd = new TaskFinancialDetails(new BigDecimal(20), false);
        TaskFinancialDetails tfd2 = new TaskFinancialDetails(new BigDecimal(10), false);
        task.setTaskFinancialDetails(tfd);
        task2.setTaskFinancialDetails(tfd2);
        TasksList taskList = new TasksList(TASKNAME, "ToDo tasks");
        taskList.getTasks().add(task);
        taskList.getTasks().add(task2);
        task.setTasksList(taskList);
        task2.setTasksList(taskList);

        //When
        tasksListDao.save(taskList);
        int id = taskList.getId();

        //Then
        Assert.assertNotEquals(0, id);

        //CleanUp
        //taskListDao.delete(id);
    }
}






