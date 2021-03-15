package com.example.demo.services;

import com.example.demo.event.TagDeletedEvent;
import com.example.demo.mapper.map.TagMapper;
import com.example.demo.persistence.tag.TagRepository;
import com.example.demo.to.TagDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagMapper tagMapper;
    private final TagRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public TagService(TagMapper tagMapper, TagRepository repository, ApplicationEventPublisher eventPublisher) {
        this.tagMapper = tagMapper;
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public TagDto saveTag(TagDto tag) {
        return saveTags(Collections.singletonList(tag)).get(0);
    }

    public List<TagDto> saveTags(List<TagDto> tags) {
        return tags.stream()
                .filter(tag -> tag.getName() != null)
                .map(tagMapper::map)
                .map(repository::save)
                .map(tagMapper::map)
                .collect(Collectors.toList());
    }

    public List<TagDto> getTags() {
        return repository.findAll().map(tagMapper::map).collect(Collectors.toList());
    }

    public List<TagDto> getTags(String tagName) {
        return repository.findAll()
                .filter(tag -> tag.getName().contains(tagName))
                .map(tagMapper::map)
                .collect(Collectors.toList());
    }

    public void deleteTag(String tagId) {
        repository.delete(tagId);
        eventPublisher.publishEvent(new TagDeletedEvent(tagId));
    }
}
