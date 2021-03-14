package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Modifying
    @Query("update Accident as a set a.name = ?1, a.text = ?2, a.address = ?3 where a.id = ?4")
    void updateAccident(String name, String text, String address, int id);
}