package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST API: Register User (http://localhost:8080/api/users/register)
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("DONE");
    }

    // GET API: Find User by ID (http://localhost:8080/api/users/{id})
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
         User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}