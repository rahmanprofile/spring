package com.rahman.learning.controller;
import com.rahman.learning.entity.Product;
import com.rahman.learning.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> fetchAllProducts() {
        return productService.fetchAllProducts();
    }

    @GetMapping("/id/{id}")
    public Product fetchProductById(@PathVariable Long id) {
        return productService.fetchProductById(id);
    }

    @DeleteMapping("/id/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
