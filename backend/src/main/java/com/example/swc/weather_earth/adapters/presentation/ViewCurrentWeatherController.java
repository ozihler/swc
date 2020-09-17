package com.example.swc.weather_earth.adapters.presentation;

import com.example.swc.weather_earth.application.use_cases.view_current_weather.FetchCurrentWeather;
import com.example.swc.weather_earth.application.use_cases.view_current_weather.ViewCurrentWeather;
import com.example.swc.weather_earth.application.use_cases.view_current_weather.ViewCurrentWeatherUseCase;
import com.example.swc.domain.Latitude;
import com.example.swc.domain.Location;
import com.example.swc.domain.Longitude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ViewCurrentWeatherController {

    private final ViewCurrentWeather viewCurrentWeather;

    @Autowired
    public ViewCurrentWeatherController(FetchCurrentWeather fetchCurrentWeather) {
        this.viewCurrentWeather = new ViewCurrentWeatherUseCase(fetchCurrentWeather);
    }


    public ResponseEntity<CurrentWeatherDto> callWith(float latitude, float longitude) {
        var input = new Location(new Latitude(latitude), new Longitude(longitude));
        var output = new RestCurrentWeatherPresenter();

        viewCurrentWeather.callWith(input, output);

        return output.getResponse();
    }
}
