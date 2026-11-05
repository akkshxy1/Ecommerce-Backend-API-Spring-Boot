package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Naya product save karne ke liye
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Saare products lene ke liye
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
