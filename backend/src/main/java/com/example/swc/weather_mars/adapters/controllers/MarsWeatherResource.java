package com.example.swc.weather_mars.adapters.controllers;

import com.example.swc.weather_mars.adapters.presenters.MarsWeatherDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarsWeatherResource {

    private final MarsWeatherController marsWeatherController;

    /**
     * https://api.nasa.gov/assets/insight/InSight%20Weather%20API%20Documentation.pdf
     * AT.av = Average Temperature in Fahrenheit
     * Season = winter, spring, summer, fall
     *
     * @param marsWeatherController
     */
    public MarsWeatherResource(MarsWeatherController marsWeatherController) {
        this.marsWeatherController = marsWeatherController;
    }

    @GetMapping("/api/mars-weather")
    public ResponseEntity<MarsWeatherDto> getCurrentMarsWeather() {
        return marsWeatherController.viewCurrentMarsWeather();
    }

}
