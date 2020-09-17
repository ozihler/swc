package com.example.swc.weather_earth.application.use_cases.view_current_weather;

import com.example.swc.domain.Location;

public interface ViewCurrentWeather {
    void callWith(Location location, CurrentWeatherPresenter output);
}
