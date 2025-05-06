package ru.rostec.prodmanage;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rostec.prodmanage.task.repository.TaskRepository;
import ru.rostec.prodmanage.task.service.TaskService;
import ru.rostec.prodmanage.task.model.Task;
import ru.rostec.prodmanage.task.model.TaskStatus;
import ru.rostec.prodmanage.task.service.TaskServiceImp;
import ru.rostec.prodmanage.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    private Task sampleTask;

    static Task task;
    static Task task2;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImp taskService;


//    @BeforeAll
//    static void setupTasks() {
//
//        Task task = Task.builder()
//                .id(1L)
//                .name("Test Task")
//                .creator(new User())
//                .status(TaskStatus.IN_PROGRESS)
//                .startDate(LocalDateTime.now())
//                .deadline(LocalDateTime.now().plusDays(3))
//                .build();
//
//
//        Task task2 = Task.builder()
//                .id(2L)
//                .name("Test Task 2")
//                .creator(new User())
//                .status(TaskStatus.ON_DESIGN)
//                .startDate(LocalDateTime.now())
//                .deadline(LocalDateTime.now().plusDays(3))
//                .build();
//    }

    @BeforeEach
    void setUp() {
        sampleTask = Task.builder()
                .id(1L)
                .name("Тестовая задача")
                .creator(new User())
                .status(TaskStatus.IN_PROGRESS)
                .startDate(LocalDateTime.now())
                .deadline(LocalDateTime.now().plusDays(3))
                .build();
    }


    @Test
    void getTaskById_returnsTask_whenFound() {

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Optional<Task> result = taskService.getTaskById(1L);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(1L, result.get().getId());
    }

    @Test
    void getTaskById_returnsEmptyOptional_whenTaskNotFound() {

        Long nonExistentId = 99999L;

        Mockito.when(taskRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Optional<Task> result = taskService.getTaskById(nonExistentId);

        Assertions.assertTrue(result.isEmpty(), "Когда задача не найдена ожидается пустой Optional");
    }


    @Test
    void createTask_savesAndReturnsTask() {
        Mockito.when(taskRepository.save(sampleTask)).thenReturn(sampleTask);
        Task result = taskService.createTask(sampleTask);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(sampleTask, result);
        Mockito.verify(taskRepository).save(task);
    }

    @Test
    void saveTask_throwsException_whenTaskIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask(null);
        });
    }

    @Test
    void deleteTaskById_deletesTask_whenExists() {
        Mockito.when(taskRepository.existsById(1L)).thenReturn(true);
        Mockito.doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTaskById(1L);
        Mockito.verify(taskRepository).deleteById(1L);
    }

    @Test
    void deleteTaskById_throwsException_whenTaskDoesNotExist() {
        Mockito.when(taskRepository.existsById(2L)).thenReturn(false);

        Assertions.assertThrows(EntityNotFoundException.class, () -> taskService.deleteTaskById(2L));
    }


}