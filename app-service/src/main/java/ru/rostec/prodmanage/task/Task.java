package ru.rostec.prodmanage.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.rostec.prodmanage.department.Department;
import ru.rostec.prodmanage.product.Product;
import ru.rostec.prodmanage.user.User;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String description;

    @Column(name = "start_data")
    private LocalDateTime startDate;

    private LocalDateTime deadline;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department departments;

    @ManyToMany
    @JoinTable(
            name = "task_products",
    joinColumns = @JoinColumn(name = "task_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id")    )
    private Set<Product> products;
}
