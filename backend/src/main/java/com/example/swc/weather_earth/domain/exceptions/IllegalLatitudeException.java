package com.example.swc.weather_earth.domain.exceptions;

public class IllegalLatitudeException extends IllegalArgumentException {
    public IllegalLatitudeException(String value) {
        super(value);
    }
}
