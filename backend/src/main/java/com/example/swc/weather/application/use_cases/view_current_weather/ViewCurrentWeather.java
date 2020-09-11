package com.example.swc.weather.application.use_cases.view_current_weather;

import com.example.swc.weather.domain.Location;

public interface ViewCurrentWeather {
    void callWith(Location location, CurrentWeatherPresenter output);
}
