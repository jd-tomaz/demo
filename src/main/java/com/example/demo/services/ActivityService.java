package com.example.demo.services;

import com.example.demo.event.ActivityDeletedEvent;
import com.example.demo.event.TagDeletedEvent;
import com.example.demo.exceptions.Exceptions;
import com.example.demo.mapper.map.ActivityMapper;
import com.example.demo.model.Activity;
import com.example.demo.model.Tag;
import com.example.demo.persistence.activity.ActivityRepository;
import com.example.demo.to.ActivityDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository repository;
    private final TagService tagService;
    private final ApplicationEventPublisher eventPublisher;
    private final ActivityMapper activityMapper;

    public ActivityService(
            ActivityMapper activityMapper,
            ActivityRepository repository,
            TagService tagService,
            ApplicationEventPublisher eventPublisher
    ) {
        this.activityMapper = activityMapper;
        this.repository = repository;
        this.tagService = tagService;
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    @Async
    public void removeTagFromActivities(TagDeletedEvent tagDeletedEvent) {
        String tagId = tagDeletedEvent.getTagId();
        repository.findAll()
                .filter(activity -> hasAnyTagWithId(activity, tagId))
                .map(activity -> removeTagFromActivity(activity, tagId))
                .forEach(repository::save);
    }

    public ActivityDto createOrUpdateActivity(ActivityDto activity) {
        if (activity.getTags() != null) {
            activity.setTags(tagService.saveTags(activity.getTags()));
        }

        Activity savedActivity = repository.save(
                activityMapper.map(activity)
        );

        return activityMapper.map(savedActivity);
    }

    public ActivityDto getActivity(String activityId) {
        return activityMapper.map(
                repository.findById(activityId)
                        .orElseThrow(Exceptions.supplyActivityNotFoundException(activityId))
        );
    }

    public List<ActivityDto> getActivities() {
        return repository.findAll()
                .map(activityMapper::map)
                .collect(Collectors.toList());
    }

    public List<ActivityDto> getActivities(List<String> tags) {
        return repository.findAll()
                .filter(activity -> hasAnyTags(activity, tags))
                .map(activityMapper::map)
                .collect(Collectors.toList());
    }

    public void deleteActivity(String activityId) {
        repository.delete(activityId);
        eventPublisher.publishEvent(new ActivityDeletedEvent(activityId));
    }

    private boolean hasAnyTags(Activity activity, List<String> tags) {
        return activity.getTags().stream()
                .map(Tag::getName)
                .anyMatch(tagName -> tags.contains(tagName.toLowerCase()));
    }

    private boolean hasAnyTagWithId(Activity activity, String tagId) {
        return activity.getTags().stream()
                .map(Tag::getId)
                .anyMatch(id -> id.equals(tagId));
    }

    private Activity removeTagFromActivity(Activity activity, String tagId) {
        return activity.setTags(
                activity.getTags().stream().
                        filter(tag -> !tag.getId().equals(tagId))
                        .collect(Collectors.toList())
        );
    }

}
