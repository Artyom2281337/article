package ru.parshin.springtest.spring1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.parshin.springtest.spring1.models.Project;
import ru.parshin.springtest.spring1.repositories.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/")
    public String projectView(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "home";
    }

    @PostMapping("/")
    public String addProject(@RequestParam String project_name, Model model){
        Project project = new Project(project_name);
        projectRepository.save(project);
        return "redirect:/";
    }

    @PostMapping("/remove{id}")
    public String deleteProject(@PathVariable(value = "id") long id, Model model){
        Project project = projectRepository.findById(id).orElseThrow();
        projectRepository.delete(project);
        return "redirect:/";
    }


}
