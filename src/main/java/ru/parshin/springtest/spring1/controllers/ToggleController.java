package ru.parshin.springtest.spring1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.parshin.springtest.spring1.models.Project;
import ru.parshin.springtest.spring1.models.Toggle;
import ru.parshin.springtest.spring1.repositories.ProjectRepository;
import ru.parshin.springtest.spring1.repositories.ToggleRepository;

import java.util.List;

@Controller
@RequestMapping("/toggle/{group_id}")
public class ToggleController {

    @Autowired
    private ToggleRepository toggleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{id}")
    public String toggleView(@PathVariable(value = "group_id") Long groupId,
                             @PathVariable(value = "id") long id, Model model) {
        List<Toggle> toggles = toggleRepository.findAll();
        model.addAttribute("toggles", toggles);
        return "group";
    }

    @PostMapping("/add")
    public String addToggle(@PathVariable(value = "group_id") String id,
                           Project project,
                           @RequestParam String toggle_name,
                            Model model){
        project = projectRepository.findById(Long.parseLong(id, 10)).orElseThrow();
        Toggle toggle = new Toggle(toggle_name);
        project.addToggle(toggle);
        projectRepository.save(project);
        return "redirect:/project/{group_id}";
    }
}
