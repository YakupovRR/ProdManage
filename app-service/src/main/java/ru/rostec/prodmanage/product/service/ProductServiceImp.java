package ru.rostec.prodmanage.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.product.ProductRepository;
import ru.rostec.prodmanage.product.model.Product;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getProductById(Long id) {
            return productRepository.findById(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);

    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return productRepository.searchByNamePart(name);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByDepartment(Department department) {
        return productRepository.findByDepartment(department);
    }

    @Override
    public List<Product> findByType(String type) {
        return productRepository.findByType(type);
    }

    @Override
    public List<Product> searchProductByTaskId(Long id) {
        return productRepository.searchProductByTaskId(id);
    }


}
