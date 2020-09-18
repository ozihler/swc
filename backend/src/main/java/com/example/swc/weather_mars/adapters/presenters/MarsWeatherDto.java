package com.example.swc.weather_mars.adapters.presenters;

import com.example.swc.weather_mars.adapters.presenters.LocationDto;

public class MarsWeatherDto {
    public String season;
    public double averageTemperatureInCelsius;
    public LocationDto location = new LocationDto();
}
