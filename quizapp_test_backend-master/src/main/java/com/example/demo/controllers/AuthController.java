package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Administrateur;
import com.example.demo.services.UserService;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody Administrateur user) {
        logger.info("Login attempt for user with email: {}", user.getEmail());
        if (userService.authenticate(user.getEmail(), user.getPassword())) {
            logger.info("Login successful for user with email: {}", user.getEmail());
            return ResponseEntity.ok("Login successful!");
        } else {
            logger.warn("Login failed for user with email: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed.");
        }
    }
}
