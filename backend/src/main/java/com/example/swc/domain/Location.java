package com.example.swc.domain;

import com.example.swc.weather_earth.domain.exceptions.IllegalLocationException;

import static java.lang.String.format;

public class Location {
    private final Latitude latitude;
    private final Longitude longitude;

    public Location(Latitude latitude, Longitude longitude) {
        if (latitude == null || longitude == null) {
            throw new IllegalLocationException(format("Both latitude and longitude need to be set! lat: %s , lon: %s", latitude, longitude));
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Latitude getLatitude() {
        return latitude;
    }

    public Longitude getLongitude() {
        return longitude;
    }
}
