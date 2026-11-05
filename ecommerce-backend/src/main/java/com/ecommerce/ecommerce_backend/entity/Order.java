package com.ecommerce.ecommerce_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kaunse user ne order place kiya hai
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Total kitne amount ka order hai
    @Column(nullable = false)
    private Double totalAmount;

    // Order ka status (jaise: PENDING, PLACED, DELIVERED)
    @Column(nullable = false)
    private String status;

    // Order kis date/time par hua
    private LocalDateTime orderDate = LocalDateTime.now();
}