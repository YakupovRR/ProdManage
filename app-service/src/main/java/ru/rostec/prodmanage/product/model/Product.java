package ru.rostec.prodmanage.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.task.model.Task;

import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;


    private String type;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "task_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks;

}