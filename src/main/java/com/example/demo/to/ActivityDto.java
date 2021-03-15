package com.example.demo.to;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class ActivityDto {
    @Nullable
    private String id;
    private String name;
    @Nullable
    private String description;
    @Nullable
    private List<TagDto> tags;
}
