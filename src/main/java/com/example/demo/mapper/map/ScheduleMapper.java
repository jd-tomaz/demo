package com.example.demo.mapper.map;

import com.example.demo.mapper.Config;
import com.example.demo.model.Schedule;
import com.example.demo.to.ActivityDto;
import com.example.demo.to.ScheduleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = Config.class)
public interface ScheduleMapper {
    @Mapping(target = "id", source = "schedule.id")
    @Mapping(target = "activityName", source = "activity.name")
    ScheduleDto map(Schedule schedule, ActivityDto activity);

    Schedule map(ScheduleDto schedule);
}
