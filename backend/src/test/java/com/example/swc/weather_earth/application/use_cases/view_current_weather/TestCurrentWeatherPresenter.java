package com.example.swc.weather_earth.application.use_cases.view_current_weather;

import com.example.swc.weather_earth.domain.CurrentWeather;

public class TestCurrentWeatherPresenter implements CurrentWeatherPresenter {
    private CurrentWeather currentWeather;

    public CurrentWeather getCurrentWeather() {
        return this.currentWeather;
    }

    @Override
    public void present(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }
}
