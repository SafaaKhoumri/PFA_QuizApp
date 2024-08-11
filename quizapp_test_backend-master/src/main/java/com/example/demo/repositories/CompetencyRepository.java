package com.example.demo.repositories;

import com.example.demo.model.Competency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetencyRepository extends JpaRepository<Competency, Long> {

    @Query("SELECT c FROM Competency c WHERE c.name LIKE %:query% AND c.role.id = :roleId AND c.level.id = :levelId AND c.role.theme.id = :themeId")
    List<Competency> findByQueryAndThemeIdAndRoleIdAndLevelId(String query, Long themeId, Long roleId, Long levelId);

    List<Competency> findByRoleIdAndLevelId(Long roleId, Long levelId);
}
