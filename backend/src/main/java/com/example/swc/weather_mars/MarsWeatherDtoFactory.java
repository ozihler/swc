package com.example.swc.weather_mars;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarsWeatherDtoFactory {
    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {
        MarsWeatherDto marsWeather = new MarsWeatherDto();
        marsWeather.location.latitude = 4.5024f;
        marsWeather.location.longitude = 135.6234f;

        for (Map.Entry<String, Object> o : object.entrySet()) {
            try {
                Integer.parseInt(o.getKey());
                Map<String, Object> value = (Map<String, Object>) o.getValue();
                marsWeather.season = (String) value.get("Season");
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        }
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


        // Degree Celsius = (degree Fahrenheit-32)*(5/9)
        marsWeather.averageTemperatureInCelsius =
                (temperatures.stream().mapToDouble(a -> a).average().getAsDouble() - 32d) * (5 / 9d);

        return marsWeather;
    }
}
