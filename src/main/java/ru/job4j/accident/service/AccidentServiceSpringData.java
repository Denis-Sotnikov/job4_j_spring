package ru.job4j.accident.service;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.*;

@Service
public class AccidentServiceSpringData {
    private final AccidentRepository accidentStore;
    private final AccidentTypeRepository accidentTypeStore;
    private final AccidentRuleRepository accidentRuleStore;

    public AccidentServiceSpringData(AccidentRepository accidentStore, AccidentTypeRepository accidentTypeStore, AccidentRuleRepository accidentRuleStore) {
        this.accidentStore = accidentStore;
        this.accidentTypeStore = accidentTypeStore;
        this.accidentRuleStore = accidentRuleStore;
    }


    public void save(Accident accident, List<String> rules) {
            accidentStore.save(this.preSaveOrUpdate(accident, rules));
    }

    public Accident preSaveOrUpdate(Accident accident, List<String> ruls) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String s : ruls) {
            ruleSet.add(this.getListRules().get(Integer.parseInt(s) - 1));
        }
        accident.setType(this.getAccidentTypes().get(accident.getType().getId() - 1));
        accident.setRules(ruleSet);
        return accident;
    }

    public Set<Rule> getActualHashSetOfRules(List<String> ruls) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String s : ruls) {
            ruleSet.add(this.getListRules().get(Integer.parseInt(s) - 1));
        }
        return ruleSet;
    }

        public List<Accident> getAll() {
            List<Accident> res = new ArrayList<>();
            accidentStore.findAll().forEach(res::add);
            return res;
        }

        public boolean update(Accident accident,  List<String> rules) {
        accidentStore.updateAccident(accident.getName(), accident.getText(), accident.getAddress(), accident.getId());
        return true;
        }

        public void saveOr(Accident accident, List<String> rules) {
            if (accident.id == 0) {
                this.save(accident, rules);
            } else {
                this.update(accident, rules);
            }
        }

        public Optional<Accident> findById(int id) {
            return accidentStore.findById(id);
        }
    public List<Rule> getListRules() {
        List<Rule> accidentRules = new ArrayList<>();
        accidentRuleStore.findAll().forEach(accidentRules::add);
        return accidentRules;
    }


    public List<AccidentType> getAccidentTypes() {
        List<AccidentType> accidentTypes = new ArrayList<>();
        accidentTypeStore.findAll().forEach(accidentTypes::add);
        return accidentTypes;
        }
}

