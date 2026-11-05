package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST API: http://localhost:8080/api/products
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok("DONE");
    }

    // GET API: http://localhost:8080/api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> Products = productService.getAllProducts();
        return ResponseEntity.ok(Products);
    }
}
