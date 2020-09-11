package com.example.swc.weather.application.use_cases.view_current_weather;

import com.example.swc.weather.domain.CurrentWeather;

public interface WeatherPresenter {
    void present(CurrentWeather currentWeather);
}
