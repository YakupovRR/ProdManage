package ru.rostec.prodmanage.task;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rostec.prodmanage.task.model.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTaskByName(@NotNull String name);

    List<Task> findTaskByStartDate(LocalDateTime startDate);

    List<Task> findTaskByStartDateBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore);

    //Поиск задач, которые связаны с продуктом
    @Query("SELECT t FROM Task t JOIN t.products p WHERE p.id = :productId")
    List<Task> findTasksByProduct(@Param("productId") Long productId);

}
