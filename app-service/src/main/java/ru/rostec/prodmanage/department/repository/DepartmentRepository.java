package ru.rostec.prodmanage.department.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rostec.prodmanage.department.model.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> searchDepartmentByName(@NonNull String name);

}
