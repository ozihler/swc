package com.example.swc.weather.application.gateways;

import com.example.swc.weather.domain.CurrentWeather;
import com.example.swc.weather.domain.Location;

public interface FetchCurrentWeather {
    CurrentWeather at(Location location);
}
