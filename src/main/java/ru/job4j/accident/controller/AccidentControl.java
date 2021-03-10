package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentServiceHbm;

import java.util.List;

@Controller
public class AccidentControl {
    private  final AccidentServiceHbm serviceHbm;

    public AccidentControl(AccidentServiceHbm serviceHbm) {
        this.serviceHbm = serviceHbm;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", serviceHbm.getAccidentTypesList());
        model.addAttribute("rules", serviceHbm.getRulesList());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("rIds") List<String> rules, @ModelAttribute Accident accident) {
        serviceHbm.saveOr(accident, rules);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", serviceHbm.getAccidentTypesList());
        model.addAttribute("rules", serviceHbm.getRulesList());
        model.addAttribute("accident", serviceHbm.findById(id));
        return "accident/update";
    }
}