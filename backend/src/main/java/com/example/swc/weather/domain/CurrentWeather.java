package com.example.swc.weather.domain;

import java.util.Map;

public class CurrentWeather {
    private Temperature temperature;

    public CurrentWeather(Temperature temperature) {
        this.temperature = temperature;
    }

    public Temperature getTemperature() {
        return this.temperature;
    }
}
