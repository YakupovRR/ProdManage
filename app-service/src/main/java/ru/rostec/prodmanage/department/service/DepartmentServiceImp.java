package ru.rostec.prodmanage.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rostec.prodmanage.department.DepartmentRepository;
import ru.rostec.prodmanage.department.model.Department;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImp implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }


    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Цех не найден"));
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
