package ru.job4j.accident.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.Store;

import java.util.List;

@Service
@Component
public class AccidentService {
    private final Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public void save(Accident accident) {
        store.add(accident);
    }

    public List<Accident> getAll() {
        return store.getAll();
    }

}
