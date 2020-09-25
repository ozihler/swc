package com.example.swc.weather_mars;

import java.util.List;

public class FahrenheitTemperatures {
    private List<Double> temperaturesInFahrenheit;

    public FahrenheitTemperatures(List<Double> temperaturesInFahrenheit) {
        this.temperaturesInFahrenheit = temperaturesInFahrenheit;
    }

    public double average() {
        return temperaturesInFahrenheit
                .stream()
                .mapToDouble(a -> a)
                .average()
                .orElse(0.0);
    }

    public double averageInCelsius() {
        // Degree Celsius = (degree Fahrenheit-32)*(5/9)
        return (average() - 32d) * (5 / 9d);
    }
}
