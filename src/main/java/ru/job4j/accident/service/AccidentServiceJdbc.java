package ru.job4j.accident.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.List;

@Service
@Component
public class AccidentServiceJdbc {
    AccidentJdbcTemplate template;

    public AccidentServiceJdbc(AccidentJdbcTemplate template) {
        this.template = template;
    }

    public void save(Accident accident, List<String> rules) {
        template.add(accident, rules);
    }

    public List<Accident> getAll() {
        return template.getAll();
    }

    public boolean update(Accident accident,  List<String> rules) {
        return template.update(accident, rules);
    }

    public void saveOr(Accident accident, List<String> rules) {
        if (accident.id == 0) {
            this.save(accident, rules);
        } else {
            this.update(accident, rules);
        }
    }

    public Accident findById(int id) {
        return template.findById(id);
    }

    public List<AccidentType> getAccidentTypesList() {
        return template.getAccidentTypes();
    }

    public List<Rule> getRulesList() {
        return template.getListRules();
    }

}
