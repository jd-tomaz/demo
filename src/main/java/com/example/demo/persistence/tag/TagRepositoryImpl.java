package com.example.demo.persistence.tag;

import com.example.demo.mapper.clone.TagCloner;
import com.example.demo.model.Tag;
import com.example.demo.persistence.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepositoryImpl extends AbstractEntityRepository<Tag> implements TagRepository {

    public TagRepositoryImpl(TagCloner cloner) {
        super(cloner);
    }

    @Override
    public Tag save(Tag tag) {
        return store.values().stream()
                .filter(storedTag -> storedTag.getName().equals(tag.getName()))
                .findAny()
                .orElseGet(() -> super.save(tag));
    }
}
