package com.example.demo.persistence.schedule;

import com.example.demo.mapper.clone.ScheduleCloner;
import com.example.demo.model.Schedule;
import com.example.demo.persistence.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepositoryImpl extends AbstractEntityRepository<Schedule> implements ScheduleRepository {
    public ScheduleRepositoryImpl(ScheduleCloner cloner) {
        super(cloner);
    }
}
