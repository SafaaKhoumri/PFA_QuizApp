package com.example.demo.repositories;
import java.util.Optional;
import com.example.demo.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findByTestIdAndCandidatId(Long testId, Long candidatId);
}
