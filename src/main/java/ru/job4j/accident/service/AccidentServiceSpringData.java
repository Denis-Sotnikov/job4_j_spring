package ru.job4j.accident.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Component
public class AccidentServiceSpringData {
    private final AccidentRepository accidentStore;
    private final AccidentTypeRepository accidentTypeStore;
    private final AccidentRuleRepository accidentRuleStore;
    private final SessionFactory sf;

    public AccidentServiceSpringData(AccidentRepository accidentStore, AccidentTypeRepository accidentTypeStore, AccidentRuleRepository accidentRuleStore, SessionFactory sf) {
        this.accidentStore = accidentStore;
        this.accidentTypeStore = accidentTypeStore;
        this.accidentRuleStore = accidentRuleStore;
        this.sf = sf;
    }


    public void save(Accident accident, List<String> rules) {
        Accident accidentT = this.preSaveOrUpdate(accident, rules);
        try (Session session = sf.openSession()) {
            session.beginTransaction();
//        accidentStore.save(accidentT);
            session.save(accidentT);
            session.getTransaction().commit();
        }
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
//
//        public List<Accident> getAll() {
//            return hbmStore.getAll();
//        }
//
//        public boolean update(Accident accident,  List<String> rules) {
//            return hbmStore.update(accident, rules);
//        }
//
//        public void saveOr(Accident accident, List<String> rules) {
//            if (accident.id == 0) {
//                this.save(accident, rules);
//            } else {
//                this.update(accident, rules);
//            }
//        }
//
//        public Accident findById(int id) {
//            return hbmStore.findById(id);
//        }
//
//        public List<AccidentType> getAccidentTypesList() {
//            return hbmStore.getAccidentTypes();
//        }
//
//        public List<Rule> getRulesList() {
//            return hbmStore.getListRules();
//        }
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

