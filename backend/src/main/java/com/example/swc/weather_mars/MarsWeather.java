package com.example.swc.weather_mars;

import com.example.swc.domain.Location;

public class MarsWeather {
    private final Location location;
    private final Season season;
    private final FahrenheitTemperatures fahrenheitTemperatures;

    public MarsWeather(Location location, Season season, FahrenheitTemperatures fahrenheitTemperatures) {
        this.location = location;
        this.season = season;
        this.fahrenheitTemperatures = fahrenheitTemperatures;
    }

    public float getLatitude() {
        return location.getLatitude().getFloatValue();
    }

    public float getLongitude() {
        return location.getLongitude().getFloatValue();
    }

    public String getSeason() {
        return season.name();
    }

    public double getAverageTemperatureInCelsius() {
        return fahrenheitTemperatures.averageInCelsius();
    }
}
