package com.example.swc.weather_mars;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AverageTemperature {
    @JsonProperty
    public double av;
}
