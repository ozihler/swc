package com.example.swc.weather_earth.adapters.data_access;

import com.example.swc.weather_earth.application.use_cases.view_current_weather.FetchCurrentWeather;
import com.example.swc.weather_earth.domain.CurrentWeather;
import com.example.swc.domain.Location;
import com.example.swc.weather_earth.domain.Temperature;
import com.example.swc.weather_earth.surrounding_systems.OpenWeatherApi;
import com.example.swc.weather_earth.surrounding_systems.OpenWeatherApiCurrentWeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class WeatherStation implements FetchCurrentWeather {
    private final OpenWeatherApi openWeatherApi;

    @Autowired
    public WeatherStation(OpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    @Override
    public CurrentWeather at(Location location) {
        OpenWeatherApiCurrentWeatherDto dto = fetchWeatherAt(location);

        return new CurrentWeather(
                new Temperature(dto.main.temp)
        );
    }

    private OpenWeatherApiCurrentWeatherDto fetchWeatherAt(Location location) {
        try {
            return openWeatherApi.getCurrentWeather(
                    location.getLatitude().getFloatValue(),
                    location.getLongitude().getFloatValue()
            );
        } catch (IOException e) {
            throw new WeatherSystemDownException("The weather system is currently unavailable. Message was " + e.getMessage());
        }
    }
}
