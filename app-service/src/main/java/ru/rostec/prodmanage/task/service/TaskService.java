package ru.rostec.prodmanage.task.service;

import org.springframework.data.domain.Pageable;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.task.model.Task;
import ru.rostec.prodmanage.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<Task> getTaskById(Long id);

    List<Task> searchTasksByName(String name);

    List<Task> searchTaskByStartDate(LocalDateTime startDate);

    List<Task> searchTaskByStartDateBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore);

    List<Task> searchTaskByDeadline(LocalDateTime deadline);

    List<Task> searchByDeadlineBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore);

    List<Task> searchTasksByProduct(Long productId);

    List<Task> searchTaskByDepartment(Department department);

    List<Task> searchTaskByCreator(User user);

    List<Task> searchTaskByExecutor(User user);

    List<Task> searchTaskByParentTask(Task parentTask, Pageable pageable);

    Task createTask(Task task);

    void deleteTaskById(Long id);


}
