package com.example.swc.weather.domain.exceptions;

public class IllegalLongitudeException extends IllegalArgumentException {
    public IllegalLongitudeException(String message) {
        super(message);
    }
}
