package com.example.swc.weather_earth.adapters.presentation;

import com.example.swc.weather_earth.application.use_cases.view_current_weather.CurrentWeatherPresenter;
import com.example.swc.weather_earth.domain.CurrentWeather;
import org.springframework.http.ResponseEntity;

public class RestCurrentWeatherPresenter implements CurrentWeatherPresenter {
    private ResponseEntity<CurrentWeatherDto> response;

    @Override
    public void present(CurrentWeather currentWeather) {
        CurrentWeatherDto dto = new CurrentWeatherDto();

        dto.temperature = currentWeather.getTemperature().getFloatValue();

        this.response = ResponseEntity.ok(dto);
    }

    public ResponseEntity<CurrentWeatherDto> getResponse() {
        return response;
    }
}
