package com.example.swc.weather_mars.adapters.controllers;

import com.example.swc.weather_mars.adapters.presenters.MarsWeatherDto;
import com.example.swc.weather_mars.application.use_cases.FetchMarsWeather;
import com.example.swc.weather_mars.adapters.presenters.RestMarsWeatherPresenter;
import com.example.swc.weather_mars.application.use_cases.ViewCurrentMarsWeatherUseCase;
import com.example.swc.weather_mars.application.use_cases.ViewCurrentMarsWeather;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MarsWeatherController {
    private ViewCurrentMarsWeather viewCurrentMarsWeather;

    public MarsWeatherController(FetchMarsWeather fetchMarsWeather) {
        viewCurrentMarsWeather = new ViewCurrentMarsWeatherUseCase(fetchMarsWeather);
    }

    public ResponseEntity<MarsWeatherDto> viewCurrentMarsWeather() {
        var output = new RestMarsWeatherPresenter();

        viewCurrentMarsWeather.invokeWith(output);

        return output.response();
    }

}
