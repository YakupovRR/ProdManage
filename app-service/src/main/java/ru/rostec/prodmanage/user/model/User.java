package ru.rostec.prodmanage.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.rostec.prodmanage.department.model.Department;


@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

}
