package com.example.demo.event;

import lombok.Data;

@Data
public class TagDeletedEvent {
    public TagDeletedEvent(String tagId) {
        this.tagId = tagId;
    }

    private String tagId;
}
