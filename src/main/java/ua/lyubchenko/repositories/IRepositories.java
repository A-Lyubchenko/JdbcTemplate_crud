package ua.lyubchenko.repositories;

import java.util.List;

public interface IRepositories<T> {

    void create(T entity);

    List<T> read();

    void update(int id, T entity);

    void delete(int id);

    T get(int id);
}
