package com.example.demo.mapper.clone;

import com.example.demo.mapper.Config;
import com.example.demo.model.Activity;
import org.mapstruct.Mapper;

@Mapper(config = Config.class)
public interface ActivityCloner extends Cloner<Activity> {
}
