package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Administrateur;
import com.example.demo.repositories.AdministrateurRepository;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AdministrateurRepository userRepository;

    public boolean authenticate(String email, String password) {
        logger.info("Authenticating user with email: {}", email);
        Optional<Administrateur> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            Administrateur user = userOpt.get();
            logger.info("User found with email: {}", email);
            boolean passwordMatches = user.getPassword().equals(password);
            if (passwordMatches) {
                logger.info("Password matches for user with email: {}", email);
            } else {
                logger.warn("Password does not match for user with email: {}", email);
            }
            return passwordMatches;
        } else {
            logger.warn("No user found with email: {}", email);
            return false;
        }
    }
}
