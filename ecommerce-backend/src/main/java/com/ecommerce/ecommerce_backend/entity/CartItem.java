package com.ecommerce.ecommerce_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kaunsa user ye item add kar raha hai
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Kaunsa product add kiya gaya hai
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Uss product ki quantity (jaise 2 quantity)
    @Column(nullable = false)
    private Integer quantity;
}
