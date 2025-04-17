package ru.rostec.prodmanage.department.service;

import ru.rostec.prodmanage.department.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartment();

    Department getDepartmentById(Long id);

    List<Department> searchDepartmentsByName(String name);

    Department createDepartment(Department department);

    void deleteDepartmentById(Long id);

}
