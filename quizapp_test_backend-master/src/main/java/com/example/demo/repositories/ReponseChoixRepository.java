package com.example.demo.repositories;

import com.example.demo.model.AnswerChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseChoixRepository extends JpaRepository<AnswerChoice, Long> {
}
