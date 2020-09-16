package com.example.swc.weather_earth.domain.exceptions;

public class IllegalLocationException extends IllegalArgumentException {
    public IllegalLocationException(String message) {
        super(message);
    }
}
