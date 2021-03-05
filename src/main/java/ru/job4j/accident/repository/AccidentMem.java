package ru.job4j.accident.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Component
public class AccidentMem implements Store<Accident> {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final List<AccidentType> accidentTypes = List.of(AccidentType.of(1, "Две машины"),
            AccidentType.of(2, "Машина и человек"),
            AccidentType.of(3, "Машина и велосипед"));

    public AccidentMem() {
        Accident first = new Accident(1, "Первый инцидент", "Все ок", "SPB", AccidentType.of(1, "Заглох"));
        accidents.put(first.getId(), first);
        Accident second = new Accident(2, "Второй инцидент", "Все ок", "USA", AccidentType.of(2, "Сломался"));
        accidents.put(second.getId(), second);
        Accident third = new Accident(3, "Третий инцидент", "Все ок", "Canada", AccidentType.of(3, "Высох"));
        accidents.put(third.getId(), third);
    }

    @Override
    public boolean update(Accident accident) {
        accident.setType(accidentTypes.get(accident.getType().getId() - 1));
        return accidents.replace(accident.getId(), accidents.get(accident.getId()), accident);
    }

    @Override
    public List getAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public Accident add(Accident accident) {
       Integer count = accidents.size();
       accident.setId(count + 1);
       accident.setType(accidentTypes.get(accident.getType().getId() - 1));
       return  accidents.putIfAbsent(count + 1, accident);
    }

    @Override
    public Accident findById(int id) {
        return accidents.get(id);
    }


}
