package springStudy.library.service;

import springStudy.library.exception.CannotDeleteForeignKeyException;

import java.util.Set;

/**
 * CRUD service interface with generic types
 * @param <T>
 * @param <ID>
 */
public interface CrudService <T, ID>{

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id) throws CannotDeleteForeignKeyException;
}
