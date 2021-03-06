package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.List;

public interface Store<T, V> {
    boolean update(T t, List<V> rules);
    List<T> getAll();
    T add(T t, List<V> rules);
    T findById(int id);
}
