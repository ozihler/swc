package com.example.swc.weather_earth.application.use_cases.view_current_weather;

import com.example.swc.weather_earth.domain.CurrentWeather;
import com.example.swc.weather_earth.domain.Location;

public interface FetchCurrentWeather {
    CurrentWeather at(Location location);
}
