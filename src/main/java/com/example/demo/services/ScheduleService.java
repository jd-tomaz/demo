package com.example.demo.services;

import com.example.demo.event.ActivityDeletedEvent;
import com.example.demo.mapper.map.ScheduleMapper;
import com.example.demo.model.Schedule;
import com.example.demo.persistence.schedule.ScheduleRepository;
import com.example.demo.to.ActivityDto;
import com.example.demo.to.ScheduleDto;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleRepository repository;
    private final ActivityService activityService;

    public ScheduleService(
            ScheduleMapper scheduleMapper,
            ScheduleRepository repository,
            ActivityService activityService
    ) {
        this.scheduleMapper = scheduleMapper;
        this.repository = repository;
        this.activityService = activityService;
    }

    @EventListener
    @Async
    public void deleteOutdatedSchedule(ActivityDeletedEvent activityDeletedEvent) {
        String activityId = activityDeletedEvent.getActivityId();
        repository.findAll()
                .filter(schedule -> schedule.getActivityId().equals(activityId))
                .map(Schedule::getId)
                .forEach(this::deleteSchedule);
    }

    public ScheduleDto createSchedule(ScheduleDto schedule) {
        Schedule createdSchedule = repository.save(scheduleMapper.map(schedule));
        return scheduleToDto(createdSchedule);
    }

    public List<ScheduleDto> getSchedules(Instant start, Instant end) {
        return repository.findAll()
                .filter(schedule -> isBetween(schedule, start, end))
                .map(this::scheduleToDto)
                .collect(Collectors.toList());
    }

    public void deleteSchedule(String scheduleId) {
        repository.delete(scheduleId);
    }

    private ScheduleDto scheduleToDto(Schedule schedule) {
        ActivityDto activity = activityService.getActivity(schedule.getActivityId());
        return scheduleMapper.map(schedule, activity);
    }

    private boolean isBetween(Schedule schedule, Instant start, Instant end) {
        return (schedule.getStart().isAfter(start) && schedule.getStart().isBefore(end)) ||
                (schedule.getEnd().isAfter(start) && schedule.getEnd().isBefore(end));
    }

}
