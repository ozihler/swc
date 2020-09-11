package com.example.swc.weather.domain;

public class Location {
    private final Latitude latitude;
    private final Longitude longitude;

    public Location(Latitude latitude, Longitude longitude) {
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
