package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentHibernate implements Store<Accident, String> {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
            return accident;
        }
    }

    @Override
    public boolean update(Accident accident, List<String> rules) {
        Accident accidentMain = this.preUpdate(accident, rules);
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(accidentMain);
            session.getTransaction().commit();
        }
        return true;
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct ac from Accident ac "
                    + "join fetch ac.type t " + "join fetch ac.rules", Accident.class)
                    .list();
        }
    }

    @Override
    public Accident add(Accident accident, List<String> rules) {
        return this.preSave(accident, rules);
    }

    public Accident preSave(Accident accident, List<String> ruls) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String s : ruls) {
            ruleSet.add(this.getListRules().get(Integer.parseInt(s) - 1));
        }
        accident.setType(this.getAccidentTypes().get(accident.getType().getId() - 1));
        accident.setRules(ruleSet);
        return this.save(accident);
    }

    public Accident preUpdate(Accident accident, List<String> ruls) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String s : ruls) {
            ruleSet.add(this.getListRules().get(Integer.parseInt(s) - 1));
        }
        accident.setType(this.getAccidentTypes().get(accident.getType().getId() - 1));
        accident.setRules(ruleSet);
        return accident;
    }

    @Override
    public Accident findById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select ac from Accident ac "
                            + "join fetch ac.type t "
                            + "join fetch ac.rules "
                            + "where ac.id = :sId", Accident.class).setParameter("sId", id).uniqueResult();
        }
    }

    public List<Rule> getListRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    public List<AccidentType> getAccidentTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }
}