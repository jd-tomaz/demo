package com.example.demo.mapper.clone;

import com.example.demo.mapper.Config;
import com.example.demo.model.Tag;
import org.mapstruct.Mapper;

@Mapper(config = Config.class)
public interface TagCloner extends Cloner<Tag> {
}
