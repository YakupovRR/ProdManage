package ru.rostec.prodmanage.user.repository;

import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.user.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUserByDepartment(Department department);

    @Query("SELECT u FROM User u WHERE LOWER (u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> searchUserByName(@Param("name")  String name);

    List<User> findUserBySupervisor(User user);

    Page<User> findAll(Pageable pageable);
}
