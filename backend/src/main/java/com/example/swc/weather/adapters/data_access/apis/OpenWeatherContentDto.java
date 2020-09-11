package com.example.swc.weather.adapters.data_access.apis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherContentDto {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        @JsonProperty
        public float temp;
    }

    @JsonProperty
    public Main main;
}
