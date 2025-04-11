package ru.rostec.prodmanage.department;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rostec.prodmanage.user.User;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findDepartmentById(Long id);

    List<Department> findDepartmentByName(@NonNull String name);


}
