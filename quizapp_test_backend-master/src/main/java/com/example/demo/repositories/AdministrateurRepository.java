package com.example.demo.repositories;

import com.example.demo.model.Administrateur;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

    Optional<Administrateur> findByEmail(String email);

}