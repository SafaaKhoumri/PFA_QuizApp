package com.example.demo.controllers;

import com.example.demo.model.Theme;
import com.example.demo.model.Role;
import com.example.demo.model.Competency;
import com.example.demo.model.Level;
import com.example.demo.repositories.ThemeRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.CompetencyRepository;
import com.example.demo.repositories.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CreateTest {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NiveauRepository levelRepository;

    @Autowired
    private CompetencyRepository competencyRepository;

    @GetMapping("/themes")
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @GetMapping("/roles/{themeId}")
    public List<Role> getRolesByThemeId(@PathVariable Long themeId) {
        return roleRepository.findByThemeId(themeId);
    }

    @GetMapping("/levels/{themeId}")
    public List<Level> getLevelsByThemeId(@PathVariable Long themeId) {
        return levelRepository.findByThemeId(themeId);
    }

    @GetMapping("/competencies/search")
    public List<Competency> searchCompetencies(@RequestParam String query, @RequestParam Long themeId,
            @RequestParam Long roleId, @RequestParam Long levelId) {
        // Utilisez le repository pour récupérer les compétences en fonction des
        // paramètres
        // Vous pouvez ajouter des méthodes dans le repository pour filtrer par query,
        // themeId, roleId et levelId
        return competencyRepository.findByQueryAndThemeIdAndRoleIdAndLevelId(query, themeId, roleId, levelId);
    }

    @GetMapping("/competencies")
    public List<Competency> getCompetencies(@RequestParam Long roleId, @RequestParam Long levelId) {
        return competencyRepository.findByRoleIdAndLevelId(roleId, levelId);
    }
}
