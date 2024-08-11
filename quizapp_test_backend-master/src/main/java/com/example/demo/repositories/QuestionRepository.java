package com.example.demo.repositories;

import com.example.demo.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.choices WHERE q.competency.id IN :competencyIds")
    List<Question> findQuestionsByCompetencyIds(List<Long> competencyIds);
}
