package ru.parshin.springtest.spring1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.parshin.springtest.spring1.models.Project;
import ru.parshin.springtest.spring1.models.Toggle;
import ru.parshin.springtest.spring1.repositories.ProjectRepository;

import java.util.List;

@Controller
@RequestMapping("/project")
public class GroupController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{id}")
    public String groupView(@PathVariable(value = "id") long id, Model model) {
        /*
        Optional<Project> project = projectRepository.findById(id);
        ArrayList<Project> res = new ArrayList<>();
        project.ifPresent(res::add);
        model.addAttribute("project", res);
        */

        Project oneProject = projectRepository.findById(id).orElseThrow();
        model.addAttribute("oneProject", oneProject);
        System.out.println(oneProject.getFullUrl(oneProject.getPath()));
        System.out.println(oneProject.getProject_name());
        System.out.println(oneProject.getId());

        List<Project> projects = projectRepository.findAll();
        model.addAttribute("allProjects", projects);
        return "group";
    }

    @PostMapping("/{id}")
    public String addGroup(@PathVariable(value = "id") long id,
                           Project parent_project,
                           @RequestParam String project_name,
                           Model model){
        Project project = new Project(project_name, id);
        parent_project = projectRepository.findById(id).orElseThrow();
        parent_project.addProjects(project);
        project.setPath(parent_project.getPath() + id);
        System.out.println(parent_project.getFullUrl(project.getPath()) + "-" + id);
        projectRepository.save(project);
        return "redirect:/project/{id}";
    }

    @PostMapping("/{parent_id}/remove{id}")
    public String deleteProject(@PathVariable(value = "parent_id") long parent_id,
                                @PathVariable(value = "id") long id,
                                Model model){
        Project project = projectRepository.findById(parent_id).orElseThrow();
        project.getProjects().remove(projectRepository.findById(id).orElseThrow());
        project = projectRepository.findById(id).orElseThrow();
        projectRepository.delete(project);
        return "redirect:/project/{parent_id}";
    }
}
