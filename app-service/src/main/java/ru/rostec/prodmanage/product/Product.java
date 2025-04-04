package ru.rostec.prodmanage.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.rostec.prodmanage.department.Department;

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

}
