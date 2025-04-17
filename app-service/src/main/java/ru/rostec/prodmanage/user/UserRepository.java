package ru.rostec.prodmanage.user;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.user.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUserByDepartment(Department department);

    List<User> findUserByName(@NotNull String name);

    List<User> findUserBySupervisor(User user);

}
