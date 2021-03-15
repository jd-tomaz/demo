package com.example.demo.persistence.activity;

import com.example.demo.mapper.clone.ActivityCloner;
import com.example.demo.model.Activity;
import com.example.demo.persistence.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityRepositoryImpl extends AbstractEntityRepository<Activity> implements ActivityRepository {
    public ActivityRepositoryImpl(ActivityCloner cloner) {
        super(cloner);
    }
}
