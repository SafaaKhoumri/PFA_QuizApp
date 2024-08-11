package com.example.demo.services;

import com.example.demo.model.Question;
import com.example.demo.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findQuestionsByCompetencyIds(List<Long> competencyIds) {
        return questionRepository.findQuestionsByCompetencyIds(competencyIds);
    }
}
