package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        AccidentService accidentService = new AccidentService();
        accidentService.addAccident(new Accident(1, "Первый инцидент", "Все ок", "SPB"));
        accidentService.addAccident(new Accident(2, "Второй инцидент", "Все ок", "USA"));
        accidentService.addAccident(new Accident(3, "Третий инцидент", "Все ок", "Canada"));
        model.addAttribute("accidents", accidentService.getAccidentMem().getAccidents());
        return "index";
    }
}