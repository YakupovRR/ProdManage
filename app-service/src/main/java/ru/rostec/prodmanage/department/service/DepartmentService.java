package ru.rostec.prodmanage.department.service;

import ru.rostec.prodmanage.department.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Optional<Department> getDepartmentById(Long id);

    List<Department> searchDepartmentsByName(String name);

    Department createDepartment(Department department);

    void deleteDepartmentById(Long id);

}
