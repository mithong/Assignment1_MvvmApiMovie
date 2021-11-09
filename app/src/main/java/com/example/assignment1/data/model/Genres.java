package com.example.assignment1.data.model;

import java.io.Serializable;

public class Genres implements Serializable {

    private final String name;

    public Genres(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
