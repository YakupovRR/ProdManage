package ru.rostec.prodmanage.utils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.product.repository.ProductRepository;
import ru.rostec.prodmanage.task.model.Task;
import ru.rostec.prodmanage.task.repository.TaskRepository;
import ru.rostec.prodmanage.department.repository.DepartmentRepository;
import ru.rostec.prodmanage.user.model.User;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CheckUtils {

    private final TaskRepository taskRepository;
    private final DepartmentRepository departmentRepository;
    private final ProductRepository productRepository;

    public void validateDateOrder(LocalDateTime startDateAfter, LocalDateTime startDateBefore) {
        if (startDateAfter.isAfter(startDateBefore)) throw new IllegalArgumentException("Первая дата должна быть" +
                "не позже второй");
    }

    public void taskNotNull(Task task) {
        if (task == null) throw new IllegalArgumentException("Task не может быть null");
    }

    public void userNotNull(User user) {
        if (user == null) throw new IllegalArgumentException("User не может быть null");
    }

    public void taskById(Long id) {
        correctId(id);
        if (!taskRepository.existsById(id)) throw new EntityNotFoundException("Не найдена задача с id " + id);
    }

    public void departmentIsExists(Department department) {
        if(!departmentRepository.existsById(department.getId()))
            throw new EntityNotFoundException("Не найден департамент с id " + department.getId());
    }

    public void productById(Long id) {
        correctId(id);
        if (!productRepository.existsById(id)) throw new EntityNotFoundException("Не найден продукт с id " + id);
    }

    public void correctName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Имя/название не может быть пустым или состоять из одних пробелов");
        if (name.length() > ValidationConstants.MAX_NAME_LENGTH)
            throw new IllegalArgumentException("Имя/название не может быть длиннее " +
                    ValidationConstants.MAX_NAME_LENGTH + "символов");
    }

    public void correctId(Long id) {
        if (id == null || id < 0) throw new IllegalArgumentException("Некорректный id: " + id);
    }

}
