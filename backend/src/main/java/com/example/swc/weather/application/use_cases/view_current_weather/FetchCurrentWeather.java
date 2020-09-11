package com.example.swc.weather.application.use_cases.view_current_weather;

import com.example.swc.weather.domain.CurrentWeather;
import com.example.swc.weather.domain.Location;

public interface FetchCurrentWeather {
    CurrentWeather at(Location location);
}
