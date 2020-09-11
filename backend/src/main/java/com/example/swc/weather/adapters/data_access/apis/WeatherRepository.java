package com.example.swc.weather.adapters.data_access.apis;

import com.example.swc.weather.application.gateways.FetchCurrentWeather;
import com.example.swc.weather.domain.CurrentWeather;
import com.example.swc.weather.domain.Location;
import com.example.swc.weather.domain.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class WeatherRepository implements FetchCurrentWeather {
    private final OpenWeatherApi openWeatherApi;

    @Autowired
    public WeatherRepository(OpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    @Override
    public CurrentWeather at(Location location) {
        OpenWeatherContentDto dto = fetchWeatherAt(location);

        return new CurrentWeather(
                new Temperature(dto.main.temp)
        );
    }

    private OpenWeatherContentDto fetchWeatherAt(Location location) {
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
