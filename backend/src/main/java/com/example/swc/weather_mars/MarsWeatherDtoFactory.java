package com.example.swc.weather_mars;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class MarsWeatherDtoFactory {

    public static final float INSIGHT_LANDING_SITE_LATITUDE = 4.5024f;
    public static final float INSIGHT_LANDING_SITE_LONGITUDE = 135.6234f;

    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {
        MarsWeatherDto marsWeather = new MarsWeatherDto();
        marsWeather.location.latitude = INSIGHT_LANDING_SITE_LATITUDE;
        marsWeather.location.longitude = INSIGHT_LANDING_SITE_LONGITUDE;

        marsWeather.season = seasonFrom(object);
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);

        var averageTemperatureInFahrenheit = averageOf(temperaturesInFahrenheit);
        marsWeather.averageTemperatureInCelsius = asCelsius(averageTemperatureInFahrenheit);

        return marsWeather;
    }

    private List<Double> temperaturesInFahrenheitFrom(Map<String, Object> object) {
        return temperatureDataPointsIn(object)
                .stream()
                .mapToDouble(this::toTemperature)
                .boxed()
                .collect(toList());
    }

    private Double toTemperature(Map<String, Object> value) {
        return (Double) ((Map<String, Object>) value.get("AT")).get("av");
    }

    private String seasonFrom(Map<String, Object> object) {
        return temperatureDataPointsIn(object)
                .stream()
                .findFirst()
                .map(this::toSeason)
                .orElse("");
    }

    private String toSeason(Map<String, Object> value) {
        return (String) value.get("Season");
    }

    private List<Map<String, Object>> temperatureDataPointsIn(Map<String, Object> object) {
        return object.entrySet()
                .stream()
                .filter(o -> hasTemperature(o.getKey()))
                .map(o -> (Map<String, Object>) o.getValue())
                .collect(toList());
    }

    private boolean hasTemperature(String dataPoint) {
        try {
            Integer.parseInt(dataPoint);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private double averageOf(List<Double> temperatures) {
        return temperatures.stream().mapToDouble(a -> a).average().orElse(0.0);
    }

    private double asCelsius(double averageTemperatureInFahrenheit) {
        // Degree Celsius = (degree Fahrenheit-32)*(5/9)
        return (averageTemperatureInFahrenheit - 32d) * (5 / 9d);
    }
}
