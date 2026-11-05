package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.entity.CartItem;
import com.ecommerce.ecommerce_backend.entity.Product;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.repository.CartRepository;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import com.ecommerce.ecommerce_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // Cart me product add karne ka method
    public CartItem addToCart(Long userId, Long productId, Integer quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new com.ecommerce.ecommerce_backend.CustomException
                        .ResourceNotFoundException("User not found with id: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new com.ecommerce.ecommerce_backend.CustomException
                        .ResourceNotFoundException("User not found with id: " + productId));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartRepository.save(cartItem);
    }

    // Specific user ka cart items dekhne ke liye
    public List<CartItem> getCartByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new com.ecommerce.ecommerce_backend.CustomException
                        .ResourceNotFoundException("User not found with id: " + userId));
        return cartRepository.findByUser(user);
    }
}