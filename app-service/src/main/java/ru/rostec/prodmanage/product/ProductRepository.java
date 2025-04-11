package ru.rostec.prodmanage.product;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rostec.prodmanage.department.Department;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(@NotNull String name);

    List<Product> findByType(String type);

    List<Product> findByDepartment(Department department);

    Product findProductById(Long id);

    //Поиск продуктов, содержащего в названии слово
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE(CONCAT('%', :namePart, '%') )")
    List<Product> searchByNamePart(@Param("namePart") String namePart);

    //Поиск продуктов по задаче
    @Query("SELECT p FROM Product p JOIN p.tasks t WHERE t.name = :taskName")
    List<Product> searchProductByTaskName(@Param("taskName") String taskName);

}
