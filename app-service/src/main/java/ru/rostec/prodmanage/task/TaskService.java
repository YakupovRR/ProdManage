package ru.rostec.prodmanage.task;

import ru.rostec.prodmanage.task.model.Task;

import java.util.List;

public interface TaskService {

    Task getTaskById(Long id);

    List<Task> searchTasksByName(String name);

    Task createTask(Task task);

    void deleteTaskById(Long id);

}
