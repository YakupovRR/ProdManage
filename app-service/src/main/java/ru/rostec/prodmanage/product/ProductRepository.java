package ru.rostec.prodmanage.product;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.product.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByType(String type);

    List<Product> findByDepartment(Department department);

    //Поиск по имени; предполагается, что имя не уникально
    List<Product> findByName(@NotNull String name);

    //Поиск продуктов, содержащего в названии слово
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE(CONCAT('%', :namePart, '%') )")
    List<Product> searchByNamePart(@Param("namePart") String namePart);

    //Поиск продуктов по имени задачи
    @Query("SELECT p FROM Product p JOIN p.tasks t WHERE t.name = :taskName")
    List<Product> searchProductByTaskName(@Param("taskName") String taskName);

    //Поиск продуктов по id задачи
    @Query("SELECT p FROM Product p JOIN p.tasks t WHERE t.id = :taskId")
    List<Product> searchProductByTaskId(@Param("taskId") Long taskId);

    Page<Product> findAll(Pageable pageable);
}
