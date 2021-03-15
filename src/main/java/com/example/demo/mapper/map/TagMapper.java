package com.example.demo.mapper.map;

import com.example.demo.mapper.Config;
import com.example.demo.model.Tag;
import com.example.demo.to.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = Config.class)
public interface TagMapper {

    TagDto map(Tag tag);

    @Mapping(target = "name", expression = "java(tag.getName().toLowerCase())")
    Tag map(TagDto tag);

}
