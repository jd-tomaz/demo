package com.example.demo.mapper.map;

import com.example.demo.mapper.Config;
import com.example.demo.model.Activity;
import com.example.demo.to.ActivityDto;
import org.mapstruct.Mapper;

@Mapper(config = Config.class, uses = TagMapper.class)
public interface ActivityMapper {

    ActivityDto map(Activity activity);

    Activity map(ActivityDto activity);



}
