package ru.rostec.prodmanage.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rostec.prodmanage.department.repository.DepartmentRepository;
import ru.rostec.prodmanage.department.model.Department;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImp implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> searchDepartmentsByName(String name) {

        return departmentRepository.searchDepartmentByName(name);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }


    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
