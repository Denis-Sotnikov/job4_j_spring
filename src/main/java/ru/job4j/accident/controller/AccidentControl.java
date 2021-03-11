package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentServiceHbm;
import ru.job4j.accident.service.AccidentServiceSpringData;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {
    private  final AccidentServiceHbm serviceHbm;
    private  final AccidentServiceSpringData springData;

    public AccidentControl(AccidentServiceHbm serviceHbm, AccidentServiceSpringData springData) {
        this.serviceHbm = serviceHbm;
        this.springData = springData;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", springData.getAccidentTypes());
        model.addAttribute("rules", springData.getListRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("rIds") List<String> rules, @ModelAttribute Accident accident) {
//        serviceHbm.saveOr(accident, rules);
        springData.save(accident, rules);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", springData.getAccidentTypes());
        model.addAttribute("rules", springData.getListRules());
        model.addAttribute("accident", serviceHbm.findById(id));
        return "accident/update";
    }
}