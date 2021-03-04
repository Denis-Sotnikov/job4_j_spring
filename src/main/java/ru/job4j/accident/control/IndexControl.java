package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        List<String> stringList = new ArrayList<>();
        stringList.add("Olga");
        stringList.add("Nika");
        stringList.add("Masha");
        model.addAttribute("listWithUsers", stringList);
        return "index";
    }
}