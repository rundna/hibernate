package com.kodilla.hibernate.taskslist.dao;
import com.kodilla.hibernate.task.Task;
import com.kodilla.hibernate.taskslist.TasksList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TasksListDao extends CrudRepository<TasksList, Integer> {

    List<TasksList> findByListName(String listName);

}
