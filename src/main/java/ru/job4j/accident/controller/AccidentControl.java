package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getAccidentTypesList());
        model.addAttribute("rules", accidentService.getRulesList());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("rIds") List<String> rules, @ModelAttribute Accident accident) {
        accidentService.saveOr(accident, rules);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit() {
        return "accident/edit";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidentService.getAccidentTypesList());
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("rules", accidentService.getRulesList());
        return "accident/update";
    }
}