package com.example.swc.weather.application.use_cases.view_current_weather;

import com.example.swc.weather.application.gateways.FetchCurrentWeather;
import com.example.swc.weather.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("View Current Weather Use Case")
class ViewCurrentWeatherUseCaseTest {

    @Test
    @DisplayName("should return the current weather for given input coordinates")
    void testCurrentWeatherCanBeFetchedForLocationByCoordinates() {
        FetchCurrentWeather fetchCurrentWeather = location -> new CurrentWeather(new Temperature(100f));

        ViewCurrentWeather useCase = new ViewCurrentWeatherUseCase(fetchCurrentWeather);

        var output = new TestCurrentWeatherPresenter();

        Location location = new Location(new Latitude(8.51f), new Longitude(47.10f));

        useCase.callWith(location, output);

        assertEquals(100, output.getCurrentWeather().getTemperature().getFloatValue());
    }

}