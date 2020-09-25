package com.example.swc.weather_mars;

public class MarsWeatherDtoFactory {

    public MarsWeatherDto createDtoFrom(MarsWeather marsWeather) {
        MarsWeatherDto dto = new MarsWeatherDto();
        dto.location.latitude = marsWeather.getLatitude();
        dto.location.longitude = marsWeather.getLongitude();
        dto.season = marsWeather.getSeason();
        dto.averageTemperatureInCelsius = marsWeather.getAverageTemperatureInCelsius();

        return dto;
    }

}
