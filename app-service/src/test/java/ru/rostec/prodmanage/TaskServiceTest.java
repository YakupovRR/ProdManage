package ru.rostec.prodmanage;

import ch.qos.logback.core.testUtil.MockInitialContext;
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
import ru.rostec.prodmanage.department.repository.DepartmentRepository;
import ru.rostec.prodmanage.product.repository.ProductRepository;
import ru.rostec.prodmanage.task.repository.TaskRepository;
import ru.rostec.prodmanage.task.service.TaskService;
import ru.rostec.prodmanage.task.model.Task;
import ru.rostec.prodmanage.task.model.TaskStatus;
import ru.rostec.prodmanage.task.service.TaskServiceImp;
import ru.rostec.prodmanage.user.model.User;
import ru.rostec.prodmanage.utils.CheckUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {


    @Mock
    private TaskRepository taskRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private CheckUtils checkUtils;

    @Mock
    private ProductRepository productRepository;


    @InjectMocks
    private TaskServiceImp taskService;

    private Task sampleTask;



    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        departmentRepository = mock(DepartmentRepository.class);
        productRepository = mock(ProductRepository.class);

        checkUtils = new CheckUtils(taskRepository, departmentRepository, productRepository);

        lenient().when(productRepository.existsById(anyLong())).thenReturn(true);
        lenient().when(departmentRepository.existsById(anyLong())).thenReturn(true);

        taskService = new TaskServiceImp(taskRepository, departmentRepository, checkUtils);

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
    void getTaskById_ShouldReturnTask_WhenIdIsCorrect() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(sampleTask));

        Optional<Task> result = taskService.getTaskById(1L);

        assertTrue(result.isPresent());
        assertEquals(sampleTask, result.get());
    }
    @Test
    void searchTasksByName_ShouldReturnList_WhenNameIsValid() {
        when(taskRepository.findTaskByName("Тестовая задача")).thenReturn(List.of(sampleTask));

        List<Task> result = taskService.searchTasksByName("Тестовая задача");

        assertEquals(1, result.size());
        assertEquals(sampleTask, result.getFirst());
    }

    @Test
    void searchTaskByStartDateBetween_ShouldReturnList_WhenDatesAreValid() {
        LocalDateTime after = LocalDateTime.now().minusDays(1);
        LocalDateTime before = LocalDateTime.now().plusDays(1);

        when(taskRepository.findTaskByStartDateBetween(after, before)).thenReturn(List.of(sampleTask));

        List<Task> result = taskService.searchTaskByStartDateBetween(after, before);

        assertEquals(1, result.size());
        assertEquals(sampleTask, result.get(0));
    }

    @Test
    void searchTasksByProduct_ShouldReturnList_WhenProductIdIsValid() {
        when(taskRepository.findTasksByProduct(1L)).thenReturn(List.of(sampleTask));

        List<Task> result = taskService.searchTasksByProduct(1L);

        assertEquals(1, result.size());
        assertEquals(sampleTask, result.get(0));
    }

    @Test
    void searchTaskByDeadline_ShouldReturnList_WhenDeadlineMatches() {
        when(taskRepository.findTaskByDeadline(sampleTask.getDeadline())).thenReturn(List.of(sampleTask));

        List<Task> result = taskService.searchTaskByDeadline(sampleTask.getDeadline());

        assertEquals(1, result.size());
        assertEquals(sampleTask, result.get(0));
    }

    @Test
    void createTask_ShouldSaveTask_WhenTaskIsValid() {
        when(taskRepository.save(sampleTask)).thenReturn(sampleTask);

        Task result = taskService.createTask(sampleTask);

        assertNotNull(result);
        assertEquals(sampleTask, result);
    }


}