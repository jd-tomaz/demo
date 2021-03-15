package com.example.demo.to;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Data
public class ScheduleDto {
    @Nullable
    private String id;
    private String activityId;
    @Nullable
    private String activityName;
    private Instant start;
    private Instant end;
}
