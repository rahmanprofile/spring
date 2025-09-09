package com.rahman.learning.service;
import com.rahman.learning.entity.Product;
import com.rahman.learning.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public  ProductRepository getProductRepository(ProductRepository repository) {
        return  this.productRepository = repository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    public Product fetchProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


    public Product updateProduct(Long id, Product product) throws Exception {
        if (id == null) {
            throw new Exception("Please enter productId");
        }

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new Exception("No product found related to productId: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());

        return productRepository.save(existingProduct);
    }

}
