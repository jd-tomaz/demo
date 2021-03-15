package com.example.demo.event;

import lombok.Data;

@Data
public class ActivityDeletedEvent {
    public ActivityDeletedEvent(String activityId) {
        this.activityId = activityId;
    }

    private String activityId;
}
