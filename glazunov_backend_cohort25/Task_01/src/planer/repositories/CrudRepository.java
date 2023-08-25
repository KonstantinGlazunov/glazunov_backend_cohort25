package planer.repositories;

import planer.models.Event;

import java.util.List;


public interface CrudRepository<T> {
    T findBiId(Long id);

    List<T> findAll();

    void save(T model);

    void deleteById(Long id);

    void upDate (T model);

}
