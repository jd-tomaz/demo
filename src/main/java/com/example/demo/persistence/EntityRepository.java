package com.example.demo.persistence;

import java.util.Optional;
import java.util.stream.Stream;

public interface EntityRepository<T> {

    void deleteAll();

    void delete(String id);

    Stream<T> findAll();

    Optional<T> findById(String id);

    <E extends T> E save(E entity);

}
