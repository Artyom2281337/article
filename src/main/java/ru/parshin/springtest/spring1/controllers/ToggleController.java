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
@RequestMapping("/toggle")
public class ToggleController {

    @Autowired
    private ToggleRepository toggleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{group_id}/{id}")
    public String toggleView(@PathVariable(value = "group_id") Long groupId,
                             @PathVariable(value = "id") long id, Model model) {
        List<Toggle> toggles = toggleRepository.findAll();
        model.addAttribute("toggles", toggles);
        return "group";
    }

    @PostMapping("/{group_id}/add")
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

    @GetMapping("/search")
    public String findToggles(@RequestParam("name") String name, Model model) {
        List<Toggle> toggles = toggleRepository.findByNameContaining(name);
        model.addAttribute("toggleList", toggles);

        return "toggles";
    }

    @PostMapping("/{group_id}/{toggle_id}/edit")
    public String editToggle(@PathVariable(value = "toggle_id") Long toggleId,
                             @PathVariable(value = "group_id") Long groupId,
                             @RequestParam("link") String link) {

        toggleRepository.findById(toggleId)
                .ifPresent(toggle -> {
                    toggle.setLink(link);
                    toggleRepository.save(toggle);
                });

        return "redirect:/project/{group_id}";
    }
}
