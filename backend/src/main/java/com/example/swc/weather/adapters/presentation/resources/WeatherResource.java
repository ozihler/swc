package com.example.swc.weather.adapters.presentation.resources;

import com.example.swc.weather.adapters.presentation.controllers.ViewCurrentWeatherController;
import com.example.swc.weather.adapters.presentation.dtos.CurrentWeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherResource {

    private ViewCurrentWeatherController viewCurrentWeatherController;

    @Autowired
    public WeatherResource(ViewCurrentWeatherController viewCurrentWeatherController) {
        this.viewCurrentWeatherController = viewCurrentWeatherController;
    }

    @GetMapping("/current-weather")
    public ResponseEntity<CurrentWeatherDto>
    getCurrentWeather(@RequestParam("lat") float lat,
                      @RequestParam("lon") float lon) {
        return viewCurrentWeatherController.callWith(lat, lon);

    }
}
