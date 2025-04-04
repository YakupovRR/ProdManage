package ru.rostec.prodmanage.task;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime startData;

    private LocalDateTime deadline;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    //ToDo продолжить созданние полей и дописать ТаскСтатус


    /*
    status_id INT REFERENCES task_statues(id),
    creator_id INT REFERENCES users(id),
    executor_id INT REFERENCES users(id),
    parent_task_id INT REFERENCES tasks(id),
    department_id INT REFERENCES departments(id),
    CONSTRAINT fk_parent_task FOREIGN KEY (parent_task_id) REFERENCES tasks(id) ON DELETE CASCADE
     */

}
