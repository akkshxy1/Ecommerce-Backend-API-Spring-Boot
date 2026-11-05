package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.entity.Order;
import com.ecommerce.ecommerce_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // POST API: Place Order (Checkout)
    // Example: http://localhost:8080/api/orders/checkout?userId=1
    @PostMapping("/checkout")
    public ResponseEntity<String> placeOrder(@RequestParam Long userId) {
           orderService.placeOrder(userId);
           return ResponseEntity.ok("DONE");
    }

    // GET API: View Orders by User ID
    // Example: http://localhost:8080/api/orders/user/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
           List<Order> orders = orderService.getOrdersByUser(userId);
           return ResponseEntity.ok(orders);
    }

    // PUT API: Update Order Status (Admin only)
    // Example: http://localhost:8080/api/orders/1/status?status=SHIPPED
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {

        Order updatedOrder = orderService.updateOrderStatus(orderId,status);
        return ResponseEntity.ok(updatedOrder);
    }
}
