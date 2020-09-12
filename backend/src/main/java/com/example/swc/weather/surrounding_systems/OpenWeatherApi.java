package com.example.swc.weather.surrounding_systems;

import com.example.swc.common.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.lang.String.format;

@Service
public class OpenWeatherApi {
    private final String apiKey;
    private final String baseUrl;

    @Autowired
    public OpenWeatherApi(
            @Value("${openweather.api.key}") String apiKey,
            @Value("${openweather.api.baseUrl}") String baseUrl
    ) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public OpenWeatherApiCurrentWeatherDto getCurrentWeather(float latitude, float longitude) throws IOException {
        String uri = format("%s?appid=%s&lat=%s&lon=%s&units=metric", baseUrl, apiKey, latitude, longitude);

        return HttpClient.invokeGet(uri, OpenWeatherApiCurrentWeatherDto.class);
    }

}
