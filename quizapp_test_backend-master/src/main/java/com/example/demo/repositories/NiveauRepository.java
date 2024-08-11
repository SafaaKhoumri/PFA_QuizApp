package com.example.demo.repositories;

import com.example.demo.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NiveauRepository extends JpaRepository<Level, Long> {
    List<Level> findByThemeId(Long themeId);
}
