package ru.rostec.prodmanage.user.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);

    List<User> findUsersByDepartment(Department department);

    List<User> searchUserByName(String name);

    List<User> findUsersBySupervisor(User user);

    User createUser(User user);

    void deleteUserById(Long id);

    Page<User> findAll(Pageable pageable);

}
