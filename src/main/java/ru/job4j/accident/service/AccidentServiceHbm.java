package ru.job4j.accident.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;

@Service
@Component
public class AccidentServiceHbm {
    AccidentHibernate hbmStore;

    public AccidentServiceHbm(AccidentHibernate hbmStore) {
        this.hbmStore = hbmStore;
    }

    public void save(Accident accident, List<String> rules) {
        hbmStore.preSave(accident, rules);
    }

    public List<Accident> getAll() {
        return hbmStore.getAll();
    }

    public boolean update(Accident accident,  List<String> rules) {
        return hbmStore.update(accident, rules);
    }

    public void saveOr(Accident accident, List<String> rules) {
        if (accident.id == 0) {
            this.save(accident, rules);
        } else {
            this.update(accident, rules);
        }
    }

    public Accident findById(int id) {
        return hbmStore.findById(id);
    }

    public List<AccidentType> getAccidentTypesList() {
        return hbmStore.getAccidentTypes();
    }

    public List<Rule> getRulesList() {
        return hbmStore.getListRules();
    }
}

