package com.example.demo.repositories;

import com.example.demo.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    boolean existsByName(String name);

    // Ajout d'une méthode pour trouver un test par son nom
    Optional<Test> findByName(String name);
}
