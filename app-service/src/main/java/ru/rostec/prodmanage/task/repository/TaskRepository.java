package ru.rostec.prodmanage.task.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rostec.prodmanage.task.model.Task;
import ru.rostec.prodmanage.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTaskByName(@NotNull String name);

    List<Task> findTaskByStartDate(LocalDateTime startDate);

    List<Task> findTaskByStartDateBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore);

    List<Task> findTaskByDeadline(LocalDateTime deadline);

    List<Task> findByDeadlineBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore);

    @Query("SELECT t FROM Task t JOIN t.departments d WHERE d.id = :departmentId")
    List<Task> findTaskByDepartment(@Param ("departmentId") Long department);

    List<Task> findTaskByCreator(User creator);

    List<Task> findTaskByExecutor(User executor);

    List<Task> findTaskByParentTask(Task parentTask, Pageable pageable);

    //Поиск задач, которые связаны с продуктом
    @Query("SELECT t FROM Task t JOIN t.products p WHERE p.id = :productId")
    List<Task> findTasksByProduct(@Param("productId") Long productId);
}
