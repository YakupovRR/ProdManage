package ru.rostec.prodmanage.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.task.model.Task;
import ru.rostec.prodmanage.task.service.TaskService;
import ru.rostec.prodmanage.user.model.User;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasksByName(@RequestParam String name) {
        return ResponseEntity.ok(taskService.searchTasksByName(name));
    }

    //Возможно нужно будет дописать обработку даты
    // @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate) {

    @GetMapping("/by-start-day")
    public ResponseEntity<List<Task>> searchTaskByStartDate(@RequestParam LocalDateTime startDate) {
        return ResponseEntity.ok(taskService.searchTaskByStartDate(startDate));
    }

    @GetMapping("/between-start-dates")
    public ResponseEntity<List<Task>> searchTaskByStartDateBetween(
            @RequestParam LocalDateTime startDateAfter,
            @RequestParam LocalDateTime startDateBefore) {
        return ResponseEntity.ok(taskService.searchTaskByStartDateBetween(startDateBefore, startDateAfter));
    }

    @GetMapping("/by-deadline")
    public ResponseEntity<List<Task>> searchTaskByDeadline(@RequestParam LocalDateTime deadline) {
        return ResponseEntity.ok(taskService.searchTaskByDeadline(deadline));
    }

    @GetMapping("/between-deadline-dates")
    public ResponseEntity<List<Task>> searchByDeadlineBetween(
            @RequestParam LocalDateTime deadlineAfter,
            @RequestParam LocalDateTime deadlineBefore) {
        return ResponseEntity.ok(taskService.searchByDeadlineBetween(deadlineAfter, deadlineBefore));
    }


    @GetMapping("/by-product")
    public ResponseEntity<List<Task>> searchTasksByProduct(@RequestParam Long productId) {
        return ResponseEntity.ok(taskService.searchTasksByProduct(productId));
    }

    @GetMapping("/by-department")
    public ResponseEntity<List<Task>> searchTaskByDepartment(@RequestParam Long id) {
        Department department = new Department();
        department.setId(id);
        return ResponseEntity.ok(taskService.searchTaskByDepartment(department));
    }

    @GetMapping("by-creator")
    public ResponseEntity<List<Task>> searchTaskByCreator(@RequestParam Long id) {
        User user = new User();
        user.setId(id);
        return ResponseEntity.ok(taskService.searchTaskByCreator(user));
    }

    @GetMapping("/by-executor")
    public ResponseEntity<List<Task>> searchTaskByExecutor(@RequestParam Long id) {
        User user = new User();
        user.setId(id);
        return ResponseEntity.ok(taskService.searchTaskByExecutor(user));
    }

    @GetMapping("/by-parent-task")
    public ResponseEntity<List<Task>> searchTaskByParentTask(
            @PageableDefault(page = 0, size = 20)
            @RequestParam Long id,
            @RequestParam Pageable pageable) {
        Task task = new Task();
        task.setId(id);
        return ResponseEntity.ok(taskService.searchTaskByParentTask(task, pageable));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.createTask(task);
        URI location = URI.create("/api/task/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

}
