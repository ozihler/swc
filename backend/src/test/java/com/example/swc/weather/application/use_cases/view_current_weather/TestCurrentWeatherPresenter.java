package com.example.swc.weather.application.use_cases.view_current_weather;

import com.example.swc.weather.domain.CurrentWeather;

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
