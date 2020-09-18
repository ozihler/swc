package com.example.swc.weather_mars.domain;

public class Season {
    private String season;

    public Season(String season) {
        this.season = season;
    }

    public String value() {
        return season;
    }
}
