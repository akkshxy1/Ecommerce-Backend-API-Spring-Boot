package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.entity.CartItem;
import com.ecommerce.ecommerce_backend.entity.Order;
import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.repository.CartRepository;
import com.ecommerce.ecommerce_backend.repository.OrderRepository;
import com.ecommerce.ecommerce_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    // Checkout / Place Order Logic
    public Order placeOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new com.ecommerce.ecommerce_backend.CustomException
                        .ResourceNotFoundException("User not found with id: " + userId));

        // 1. User ke cart ke saare items nikalein
        List<CartItem> cartItems = cartRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty! Cannot place order.");
        }

        // 2. Total amount calculate karein
        double totalAmount = 0.0;
        for (CartItem item : cartItems) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }

        // 3. New Order object banayein aur save karein
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);

        // 4. Order place hone ke baad cart ko khali (delete) kar dein
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }

    // User ke saare orders dekhne ke liye
    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new com.ecommerce.ecommerce_backend.CustomException
                        .ResourceNotFoundException("User not found with id: " + userId));
        return orderRepository.findByUser(user);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new com.ecommerce.ecommerce_backend.CustomException
                        .ResourceNotFoundException("Order not found with id: " + orderId));

        order.setStatus(status);
        return orderRepository.save(order);
    }
}