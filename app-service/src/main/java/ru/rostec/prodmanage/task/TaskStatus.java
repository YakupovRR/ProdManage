package ru.rostec.prodmanage.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_statues")
@NoArgsConstructor
public class TaskStatus {

    @Id
    @GeneratedValue
    private int id;

}
