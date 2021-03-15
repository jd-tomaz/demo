package com.example.demo.exceptions;

import java.util.function.Supplier;

public class Exceptions {

    public static Supplier<NotFoundException> supplyActivityNotFoundException(String id) {
        return () -> new NotFoundException("Could not found an activity with id " + id);
    }

}
