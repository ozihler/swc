package com.example.swc.weather.domain;

public class CurrentWeather {
    private final Temperature temperature;

    public CurrentWeather(Temperature temperature) {
        this.temperature = temperature;
    }

    public Temperature getTemperature() {
        return this.temperature;
    }
}
