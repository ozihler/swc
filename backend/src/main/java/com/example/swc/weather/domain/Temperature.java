package com.example.swc.weather.domain;

public class Temperature {
    private final float value;

    public Temperature(float value) {
        this.value = value;
    }

    public float getFloatValue() {
        return this.value;
    }
}
