package com.example.swc.weather_earth.surrounding_systems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherApiCurrentWeatherDto {
    @JsonProperty
    public Main main;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        @JsonProperty
        public float temp;
    }
}
