package ru.job4j.accident.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Component
public class AccidentMem implements Store<Accident> {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        Accident first = new Accident(1, "Первый инцидент", "Все ок", "SPB");
        accidents.put(first.getId(), first);
        Accident second = new Accident(2, "Второй инцидент", "Все ок", "USA");
        accidents.put(second.getId(), second);
        Accident third = new Accident(3, "Третий инцидент", "Все ок", "Canada");
        accidents.put(third.getId(), third);
    }

    @Override
    public void update(Accident accident) {
        if (accidents.containsKey(accident.getId())) {
            accidents.put(accident.getId(), accident);
        }
    }

    @Override
    public List getAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public Accident add(Accident accident) {
        accidents.put(accident.getId(), accident);
        return accident;
    }
}
