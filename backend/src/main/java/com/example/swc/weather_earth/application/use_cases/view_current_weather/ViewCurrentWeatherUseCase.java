package com.example.swc.weather_earth.application.use_cases.view_current_weather;

import com.example.swc.weather_earth.domain.CurrentWeather;
import com.example.swc.weather_earth.domain.Location;

public class ViewCurrentWeatherUseCase implements ViewCurrentWeather {
    private final FetchCurrentWeather fetchCurrentWeather;

    public ViewCurrentWeatherUseCase(FetchCurrentWeather fetchCurrentWeather) {
        this.fetchCurrentWeather = fetchCurrentWeather;
    }

    @Override
    public void callWith(Location location, CurrentWeatherPresenter output) {
        CurrentWeather currentWeather = fetchCurrentWeather.at(location);

        output.present(currentWeather);
    }

}
