package com.example.demo.services;

import com.example.demo.model.Test;
import com.example.demo.model.Condidats;
import com.example.demo.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private TestRepository testRepository;

    // Retourner une liste de tous les tests
    public List<Test> getAllTests() {
        List<Test> tests = testRepository.findAll();
        logger.info("Number of tests found: {}", tests.size());
        for (Test test : tests) {
            logger.info("Test ID: {}, Test Name: {}", test.getId(), test.getName());
        }
        return tests;
    }

    // Retourner une liste de tous les tests avec leurs détails
    public List<Test> getTestsWithDetails() {
        List<Test> tests = testRepository.findAll();
        for (Test test : tests) {
            logger.info("Test ID: {}, Test Name: {}, Domain: {}, Role: {}, Level: {}, Admin: {}",
                    test.getId(),
                    test.getName(),
                    test.getDomaine() != null ? test.getDomaine().getName() : "Non disponible",
                    test.getRole() != null ? test.getRole().getName() : "Non disponible",
                    test.getLevel() != null ? test.getLevel().getName() : "Non disponible",
                    test.getAdministrator() != null ? test.getAdministrator().getEmail() : "Non disponible");
        }
        return tests;
    }

    // Retourner un test spécifique par son ID
    public Optional<Test> getTestById(Long id) {
        return testRepository.findById(id);
    }

    // Retourner une liste de candidats pour un test spécifique
    public List<Condidats> getCandidatesByTestId(Long id) {
        Optional<Test> testOpt = getTestById(id);
        return testOpt.map(Test::getCandidates).orElse(null);
    }

    // Vérifier si un test existe par son nom
    public boolean doesTestExistByName(String testName) {
        return testRepository.existsByName(testName);
    }

    // Vérifier l'existence d'un test par son nom
    public boolean testNameExists(String name) {
        return testRepository.existsByName(name);
    }
}
