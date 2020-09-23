package com.example.swc.weather_mars;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarsWeatherDtoFactory {

    public static final float INSIGHT_LANDING_SITE_LATITUDE = 4.5024f;
    public static final float INSIGHT_LANDING_SITE_LONGITUDE = 135.6234f;

    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {
        MarsWeatherDto marsWeather = new MarsWeatherDto();
        marsWeather.location.latitude = INSIGHT_LANDING_SITE_LATITUDE;
        marsWeather.location.longitude = INSIGHT_LANDING_SITE_LONGITUDE;

        marsWeather.season = seasonFrom(object);
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);

        var averageTemperatureInFahrenheit = average(temperaturesInFahrenheit);
        marsWeather.averageTemperatureInCelsius = asCelsius(averageTemperatureInFahrenheit);

        return marsWeather;
    }

    private List<Double> temperaturesInFahrenheitFrom(Map<String, Object> object) {
        List<Double> temperatures = new ArrayList<>();
        for (Map.Entry<String, Object> o : object.entrySet()) {
            try {
                Integer.parseInt(o.getKey());
                Map<String, Object> value = (Map<String, Object>) o.getValue();
                Map<String, Object> at = (Map<String, Object>) value.get("AT");
                double av = (Double) at.get("av");
                temperatures.add(av);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return temperatures;
    }

    private String seasonFrom(Map<String, Object> object) {
        String season = "";
        for (Map.Entry<String, Object> o : object.entrySet()) {
            try {
                Integer.parseInt(o.getKey());
                Map<String, Object> value = (Map<String, Object>) o.getValue();
                season = (String) value.get("Season");
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return season;
    }

    private double average(List<Double> temperatures) {
        return temperatures.stream().mapToDouble(a -> a).average().orElse(0.0);
    }

    private double asCelsius(double averageTemperatureInFahrenheit) {
        // Degree Celsius = (degree Fahrenheit-32)*(5/9)
        return (averageTemperatureInFahrenheit - 32d) * (5 / 9d);
    }
}
