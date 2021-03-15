package com.example.demo.to;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class TagDto {
    @Nullable
    private String id;
    private String name;
}
