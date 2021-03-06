package com.eddiefiggie.flowplanner;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t, String path);

    void update(T t, String[] params);

    void delete(T t, String path);

}
