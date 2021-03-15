package com.example.demo.persistence;

import com.example.demo.mapper.clone.ActivityCloner;
import com.example.demo.mapper.clone.Cloner;
import com.example.demo.model.Entity;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public abstract class AbstractEntityRepository<T extends Entity> implements EntityRepository<T> {

    protected final Map<String, T> store = new ConcurrentHashMap<>();
    private final Cloner<T> cloner;

    public AbstractEntityRepository(Cloner<T> cloner) {
        this.cloner = cloner;
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

    @Override
    public void delete(String id) {
        store.remove(id);
    }

    @Override
    public Stream<T> findAll() {
        return store.values().stream().filter(Objects::nonNull).map(cloner::clone);
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(store.get(id)).map(cloner::clone);
    }

    @Override
    public <E extends T> E save(E entity) {
        String id = entity.getId();
        if (id == null) {
            do {
                id = UUID.randomUUID().toString();
            } while (store.containsKey(id));
        }

        entity.setId(id);
        store.put(id, cloner.clone(entity));
        return entity;
    }

}
