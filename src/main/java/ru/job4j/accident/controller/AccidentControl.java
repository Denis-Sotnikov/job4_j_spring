package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentServiceJdbc;

import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentServiceJdbc accidentServiceJdbc;

    public AccidentControl(AccidentServiceJdbc accidentServiceJdbc) {
        this.accidentServiceJdbc = accidentServiceJdbc;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentServiceJdbc.getAccidentTypesList());
        model.addAttribute("rules", accidentServiceJdbc.getRulesList());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("rIds") List<String> rules, @ModelAttribute Accident accident) {
        accidentServiceJdbc.saveOr(accident, rules);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidentServiceJdbc.getAccidentTypesList());
        model.addAttribute("rules", accidentServiceJdbc.getRulesList());
        model.addAttribute("accident", accidentServiceJdbc.findById(id));
        return "accident/update";
    }
}