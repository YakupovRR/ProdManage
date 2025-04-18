package ru.rostec.prodmanage.task.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.task.model.Task;
import ru.rostec.prodmanage.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<Task> getTaskById(Long id);

    List<Task> searchTasksByName(String name);

    List<Task> findTaskByStartDate(LocalDateTime startDate);

    List<Task> findTaskByStartDateBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore);

    List<Task> findTasksByProduct(Long productId);

    List<Task> findTaskByDeadline(LocalDateTime deadline);

    List<Task> findByDeadlineBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore);


    List<Task> findTaskByDepartment(Department department);

    List<Task> findTaskByCreator(User user);

    List<Task> findTaskByExecutor(User user);

    List<Task> findTaskByParentTask(Task parentTask, Pageable pageable);

    Task createTask(Task task);

    void deleteTaskById(Long id);

}
