package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Condidats candidat;

    @ManyToOne
    private Test test;

    private int correctAnswers;
    private int totalQuestions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Condidats getCandidat() {
        return candidat;
    }

    public void setCandidat(Condidats candidat) {
        this.candidat = candidat;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScorePercentage() {
        if (totalQuestions == 0) return 0;
        return (correctAnswers * 100) / totalQuestions;
    }
}
