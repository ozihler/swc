package com.example.swc.weather.domain.exceptions;

public class IllegalLatitudeException extends IllegalArgumentException {
    public IllegalLatitudeException(String value) {
        super(value);
    }
}
