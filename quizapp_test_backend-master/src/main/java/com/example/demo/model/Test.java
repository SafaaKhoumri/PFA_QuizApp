// Test.java
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    

    private Administrateur administrator;

    @ManyToOne
    @JoinColumn(name = "domaine_id")
   

    private Theme domaine;

    @ManyToOne
    @JoinColumn(name = "role_id")
    

    private Role role;

    @ManyToOne
    @JoinColumn(name = "level_id")
   

    private Level level;

    @ManyToMany
    @JoinTable(name = "test_competency", joinColumns = @JoinColumn(name = "test_id"), inverseJoinColumns = @JoinColumn(name = "competency_id"))
    @JsonIgnore

    private List<Competency> competencies;

    @ManyToMany
    @JoinTable(name = "candidate_test", joinColumns = @JoinColumn(name = "test_id"), inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    @JsonIgnore

    private List<Condidats> candidates;

    @OneToMany(mappedBy = "test")
    @JsonManagedReference
    

    private List<Question> questions;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Administrateur getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrateur administrator) {
        this.administrator = administrator;
    }

    public Theme getDomaine() {
        return domaine;
    }

    public void setDomaine(Theme domaine) {
        this.domaine = domaine;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Competency> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(List<Competency> competencies) {
        this.competencies = competencies;
    }

    public List<Condidats> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Condidats> candidates) {
        this.candidates = candidates;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
