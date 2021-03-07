package ru.job4j.accident.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.Store;

import java.util.List;

@Service
@Component
public class AccidentService {
    private final AccidentMem mem;

    public AccidentService(AccidentMem mem) {
        this.mem = mem;
    }

    public void save(Accident accident, List<String> rules) {
        mem.add(accident, rules);
    }

    public List<Accident> getAll() {
        return mem.getAll();
    }

    public boolean update(Accident accident,  List<String> rules) {
        return mem.update(accident, rules);
    }

    public void saveOr(Accident accident, List<String> rules) {
        if (accident.id == 0) {
            this.save(accident, rules);
        } else {
            this.update(accident, rules);
        }
    }

    public Accident findById(int id) {
        return mem.findById(id);
    }

    public List<AccidentType> getAccidentTypesList() {
        return mem.getAccidentTypes();
    }

    public List<Rule> getRulesList() {
        return mem.getRules();
    }
}
