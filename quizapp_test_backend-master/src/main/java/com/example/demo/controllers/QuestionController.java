package com.example.demo.controllers;

import com.example.demo.model.Question;
import com.example.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public List<Question> getQuestionsByCompetencyIds(@RequestParam("competencyIds") String competencyIds) {
        List<Long> ids = Arrays.stream(competencyIds.split(","))
                .map(Long::parseLong)
                .toList();
        return questionService.findQuestionsByCompetencyIds(ids);
    }

}
