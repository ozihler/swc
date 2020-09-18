package com.example.swc.weather_mars.domain;

public class Temperature {

    public double temperatureInFahrenheit;

    public Temperature(double temperatureInFahrenheit) {
        this.temperatureInFahrenheit = temperatureInFahrenheit;
    }

    public double inDegreeCelsius() {
        return (temperatureInFahrenheit - 32d) * (5 / 9d);
    }
}
