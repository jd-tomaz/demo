package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
public class Schedule extends Entity {
    private String activityId;
    private Instant start;
    private Instant end;
}
