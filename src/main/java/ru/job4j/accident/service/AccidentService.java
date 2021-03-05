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
    private final AccidentMem mem;

    public AccidentService(AccidentMem mem) {
        this.mem = mem;
    }

    public void save(Accident accident) {
        mem.add(accident);
    }

    public List<Accident> getAll() {
        return mem.getAll();
    }

    public boolean update(Accident accident) {
        return mem.update(accident);
    }

    public Accident findById(int id) {
        return mem.findById(id);
    }

}
