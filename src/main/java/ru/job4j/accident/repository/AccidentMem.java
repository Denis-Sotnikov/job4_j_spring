package ru.job4j.accident.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
@Component
public class AccidentMem implements Store<Accident, String> {
    AtomicInteger atomicInt = new AtomicInteger();
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final List<AccidentType> accidentTypes = List.of(AccidentType.of(1, "Две машины"),
            AccidentType.of(2, "Машина и человек"),
            AccidentType.of(3, "Машина и велосипед"));
    private final List<Rule> rules = List.of(Rule.of(1, "Статья. 1"),
            Rule.of(2, "Статья. 2"),
            Rule.of(3, "Статья. 3"));

    public AccidentMem() {
        Accident first = new Accident(1, "Первый инцидент", "Все ок", "SPB", accidentTypes.get(0), Set.of(Rule.of(1, "Статья 1")));
        accidents.put(first.getId(), first);
        Accident second = new Accident(2, "Второй инцидент", "Все ок", "USA", accidentTypes.get(1), Set.of(Rule.of(1, "Статья 1")));
        accidents.put(second.getId(), second);
        Accident third = new Accident(3, "Третий инцидент", "Все ок", "Canada", accidentTypes.get(2), Set.of(Rule.of(1, "Статья 1")));
        accidents.put(third.getId(), third);
    }

    @Override
    public boolean update(Accident accident, List<String> ruls) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String s : ruls) {
            ruleSet.add(rules.get(Integer.parseInt(s) - 1));
        }
        accident.setType(accidentTypes.get(accident.getType().getId() - 1));
        accident.setRules(ruleSet);
        System.out.println(accident);
        return accidents.replace(accident.getId(), accidents.get(accident.getId()), accident);
    }

    @Override
    public List getAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public Accident add(Accident accident, List<String> ruls) {
       Set<Rule> ruleSet = new HashSet<>();
        for (String s : ruls) {
            ruleSet.add(rules.get(Integer.parseInt(s) - 1));
        }
       atomicInt.set(accidents.size());
       accident.setId(atomicInt.incrementAndGet());
       accident.setType(accidentTypes.get(accident.getType().getId() - 1));
       accident.setRules(ruleSet);
       return  accidents.putIfAbsent(atomicInt.get(), accident);
    }

    @Override
    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentTypes;
    }

    public List<Rule> getRules() {
        return rules;
    }
}
