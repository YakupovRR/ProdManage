package ru.rostec.prodmanage.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.product.model.Product;
import ru.rostec.prodmanage.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<Product> getAllProducts(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find")
    public ResponseEntity<List<Product>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchProductsByName(name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @DeleteMapping
    ResponseEntity<Void> deleteProductById(@RequestParam Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-department/{departmentId}")
    public ResponseEntity<List<Product>> findByDepartment(@PathVariable Long departmentId) {
        Department department = new Department();
        department.setId(departmentId);
        return ResponseEntity.ok(productService.findByDepartment(department));
    }

    @GetMapping("/by-type/{type}")
    public ResponseEntity<List<Product>> findByType(@PathVariable String type) {
        return ResponseEntity.ok(productService.findByType(type));
    }

    @GetMapping("/by-task/{taskId}")
    public ResponseEntity<List<Product>> searchProductByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(productService.searchProductByTaskId(taskId));
    }


    /*Todo: Допилить когда подключу Spring Security
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
*/
}
