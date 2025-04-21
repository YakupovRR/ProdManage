package ru.rostec.prodmanage.product.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> getProductById(Long id);

    Page<Product> getAllProducts(Pageable pageable);

    List<Product> findByName(String name);

    List<Product> searchProductsByName(String name);

    Product createProduct(Product product);

    void deleteProductById(Long id);

    List<Product> findByDepartment(Department department);

    List<Product> findByType(String type);

    List<Product> searchProductByTaskId(Long id);


    //Ахтунг! Только для админов!!!
    List<Product> getAllProducts();

}
