package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

@Service
public class AccidentService {
    private AccidentMem accidentMem = new AccidentMem();
    public boolean addAccident(Accident accident) {
        accidentMem.getAccidents().put(accident.getId(), accident);
        return true;
    }

    public AccidentService() {
    }

    public AccidentMem getAccidentMem() {
        return accidentMem;
    }

    public void setAccidentMem(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }
}
