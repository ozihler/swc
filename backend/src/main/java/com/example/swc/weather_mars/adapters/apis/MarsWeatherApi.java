package com.example.swc.weather_mars.adapters.apis;

import com.example.swc.common.HttpRequest;
import com.example.swc.domain.Latitude;
import com.example.swc.domain.Location;
import com.example.swc.domain.Longitude;
import com.example.swc.weather_mars.application.use_cases.FetchMarsWeather;
import com.example.swc.weather_mars.domain.MarsWeather;
import com.example.swc.weather_mars.domain.Season;
import com.example.swc.weather_mars.domain.Temperature;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MarsWeatherApi implements FetchMarsWeather {
    private final String apiKey;
    private final String baseUrl;
    public static final Location IN_SIGHT_LANDING_SITE_LOCATION = new Location(new Latitude(4.5024f), new Longitude(135.6234f));

    public MarsWeatherApi(
            @Value("${mars.weather.api.key}") String apiKey,
            @Value("${mars.weather.api.baseUrl}") String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public MarsWeather forToday() {
        Map<String, Object> weatherData = fetchWeather();

        return new MarsWeather(
                IN_SIGHT_LANDING_SITE_LOCATION,
                new Season(season(weatherData)),
                new Temperature(averagetemperature(weatherData)));
    }

    private Map<String, Object> fetchWeather() {
        String url = String.format("%s?api_key=%s&feedtype=json&ver=1.0", this.baseUrl, this.apiKey);

        Map<String, Object> object = null;
        try {
            object = new HttpRequest<Map<String, Object>>(url).getAsType(new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public double averagetemperature(Map<String, Object> object) {
        List<Double> temperatures = new ArrayList<>();
        for (Map.Entry<String, Object> o : object.entrySet()) {
            try {
                Integer.parseInt(o.getKey());
                Map<String, Object> value = (Map<String, Object>) o.getValue();
                Map<String, Object> at = (Map<String, Object>) value.get("AT");
                double av = (Double) at.get("av");
                temperatures.add(av);
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        }

        return temperatures.stream().mapToDouble(a -> a).average().getAsDouble();
    }

    private String season(Map<String, Object> object) {
        for (Map.Entry<String, Object> o : object.entrySet()) {
            try {
                Integer.parseInt(o.getKey());
                Map<String, Object> value = (Map<String, Object>) o.getValue();
                return (String) value.get("Season");
            } catch (NumberFormatException e) {
                continue;
            }
        }

        return "";
    }
}
