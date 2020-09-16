package com.example.swc.weather_earth.domain.exceptions;

public class IllegalLongitudeException extends IllegalArgumentException {
    public IllegalLongitudeException(String message) {
        super(message);
    }
}
