package com.example.swc.weather.application.use_cases.view_current_weather;

import com.example.swc.weather.domain.CurrentWeather;

public class TestWeatherPresenter implements WeatherPresenter {
    private CurrentWeather currentWeather;

    public CurrentWeather getCurrentWeather() {
        return this.currentWeather;
    }

    @Override
    public void present(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }
}
