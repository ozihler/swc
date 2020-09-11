package com.example.swc.weather.domain.exceptions;

public class IllegalLocationException extends IllegalArgumentException {
    public IllegalLocationException(String message) {
        super(message);
    }
}
