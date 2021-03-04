package ru.job4j.accident.repository;

import java.util.List;

public interface Store<T> {
    void update(T t);
    List<T> getAll();
    T add(T t);
}
