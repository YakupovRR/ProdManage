package ru.rostec.prodmanage.task.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.task.repository.TaskRepository;
import ru.rostec.prodmanage.task.model.Task;
import ru.rostec.prodmanage.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {


    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> searchTasksByName(String name) {
        return taskRepository.findTaskByName(name);
    }

    @Override
    public List<Task> searchTaskByStartDate(LocalDateTime startDate) {
        return taskRepository.findTaskByStartDate(startDate);
    }

    @Override
    public List<Task> searchTaskByStartDateBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore) {
        return taskRepository.findTaskByStartDateBetween(startDateAfter, startDateBefore);
    }

    @Override
    public List<Task> searchTasksByProduct(Long productId) {
        return taskRepository.findTasksByProduct(productId);
    }

    @Override
    public List<Task> searchTaskByDeadline(LocalDateTime deadline) {
        return taskRepository.findTaskByDeadline(deadline);
    }

    @Override
    public List<Task> searchByDeadlineBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore) {
        return taskRepository.findByDeadlineBetween(startDateAfter, startDateBefore);
    }

    @Override
    public List<Task> searchTaskByDepartment(Department department) {
        return taskRepository.findTaskByDepartment(department.getId());
    }

    @Override
    public List<Task> searchTaskByCreator(User user) {
        return taskRepository.findTaskByCreator(user);
    }

    @Override
    public List<Task> searchTaskByExecutor(User user) {
        return taskRepository.findTaskByExecutor(user);
    }

    @Override
    public List<Task> searchTaskByParentTask(Task parentTask, Pageable pageable) {
        return taskRepository.findTaskByParentTask(parentTask, pageable);
    }

    @Override
    public Task createTask(Task task) {
        if (task == null) throw new IllegalArgumentException("Task не может быть null");
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        if(!taskRepository.existsById(id)) throw new EntityNotFoundException("Не найдена задача с id " + id);
        taskRepository.deleteById(id);
    }
}
