package com.example.swc.domain;

import com.example.swc.weather_earth.domain.exceptions.IllegalLatitudeException;

import static java.lang.String.format;

public class Latitude {
    private final float value;

    public Latitude(float value) {
        if (value < -90 || value > 90) {
            throw new IllegalLatitudeException(format("Latitude cannot be %s", value));
        }
        this.value = value;
    }

    public float getFloatValue() {
        return value;
    }
}
