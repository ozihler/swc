package com.example.swc.weather_mars.domain;

import com.example.swc.domain.Location;

public class MarsWeather {
    private final Location location;
    private final Season season;
    private final Temperature temperature;

    public MarsWeather(Location location, Season season, Temperature temperature) {
        this.location = location;
        this.season = season;
        this.temperature = temperature;
    }

    public Location getLocation() {
        return location;
    }

    public Season getSeason() {
        return season;
    }

    public Temperature getTemperature() {
        return temperature;
    }
}
