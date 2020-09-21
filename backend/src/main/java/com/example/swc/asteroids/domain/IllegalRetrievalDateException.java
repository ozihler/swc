package com.example.swc.asteroids.domain;

public class IllegalRetrievalDateException extends IllegalArgumentException {
    public IllegalRetrievalDateException(String message) {
        super(message);
    }
}
