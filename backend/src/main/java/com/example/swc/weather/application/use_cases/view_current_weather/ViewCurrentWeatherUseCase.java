package com.example.swc.weather.application.use_cases.view_current_weather;

import com.example.swc.weather.domain.CurrentWeather;
import com.example.swc.weather.domain.Location;

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
