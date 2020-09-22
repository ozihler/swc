package com.example.swc.asteroids.domain;

public class Id {
    private String id;

    public Id(String id) {
        this.id = id;
    }

    public String getValue() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
