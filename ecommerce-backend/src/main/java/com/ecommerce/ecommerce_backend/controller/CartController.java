package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.entity.CartItem;
import com.ecommerce.ecommerce_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // POST API: Add to Cart (URL me userId, productId, aur quantity denge)
    // Example: http://localhost:8080/api/cart/add?userId=1&productId=1&quantity=2
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long userId,
                                           @RequestParam Long productId,
                                           @RequestParam Integer quantity) {
        cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok("DONE");
    }

    // GET API: View Cart by User ID
    // Example: http://localhost:8080/api/cart/user/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItem>> getCartByUser(@PathVariable Long userId) {
        List<CartItem> cartitems = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cartitems);
    }
}
