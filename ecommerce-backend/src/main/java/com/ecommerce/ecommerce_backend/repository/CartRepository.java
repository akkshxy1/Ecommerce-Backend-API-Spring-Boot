package com.ecommerce.ecommerce_backend.repository;

import com.ecommerce.ecommerce_backend.entity.CartItem;
import com.ecommerce.ecommerce_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

    // Kisi specific user ka pura cart nikalne ke liye
    List<CartItem> findByUser(User user);
}
