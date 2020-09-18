package com.example.swc.weather_mars.adapters.presenters;

import com.example.swc.weather_mars.application.use_cases.MarsWeatherPresenter;
import com.example.swc.weather_mars.domain.MarsWeather;
import org.springframework.http.ResponseEntity;

public class RestMarsWeatherPresenter implements MarsWeatherPresenter {

    private ResponseEntity<MarsWeatherDto> response;

    @Override
    public void present(MarsWeather marsWeather) {
        MarsWeatherDto marsWeatherDto = new MarsWeatherDto();
        marsWeatherDto.location.latitude = marsWeather.getLocation().getLatitude().getFloatValue();
        marsWeatherDto.location.longitude = marsWeather.getLocation().getLongitude().getFloatValue();
        marsWeatherDto.season = marsWeather.getSeason().value();
        marsWeatherDto.averageTemperatureInCelsius = marsWeather.getTemperature().inDegreeCelsius();

        response = ResponseEntity.ok(marsWeatherDto);
    }

    public ResponseEntity<MarsWeatherDto> response() {
        return response;
    }
}
