package com.example.swc.asteroids.domain;

public class Name {
    private String name;

    public Name(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
