package com.example.swc.weather_mars.application.use_cases;

import com.example.swc.weather_mars.domain.MarsWeather;

public class ViewCurrentMarsWeatherUseCase implements ViewCurrentMarsWeather {
    private FetchMarsWeather fetchMarsWeather;

    public ViewCurrentMarsWeatherUseCase(FetchMarsWeather fetchMarsWeather) {
        this.fetchMarsWeather = fetchMarsWeather;
    }

    @Override
    public void invokeWith(MarsWeatherPresenter output) {
        MarsWeather marsWeather = fetchMarsWeather.forToday();

        output.present(marsWeather);
    }
}
