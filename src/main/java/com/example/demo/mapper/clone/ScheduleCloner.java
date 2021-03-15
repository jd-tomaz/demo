package com.example.demo.mapper.clone;

import com.example.demo.mapper.Config;
import com.example.demo.model.Schedule;
import org.mapstruct.Mapper;

@Mapper(config = Config.class)
public interface ScheduleCloner extends Cloner<Schedule> {
}
