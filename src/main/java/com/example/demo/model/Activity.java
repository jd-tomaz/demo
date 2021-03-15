package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
public class Activity extends Entity {
    private String name;
    private String description;
    private List<Tag> tags;
}
