package com.example.swc.domain;

import com.example.swc.weather_earth.domain.exceptions.IllegalLongitudeException;

import static java.lang.String.format;

public class Longitude {
    private final float value;

    public Longitude(float value) {
        if (value < -180 || value > 180) {
            throw new IllegalLongitudeException(format("Longitude cannot be %s", value));
        }
        this.value = value;
    }

    public float getFloatValue() {
        return value;
    }
}
