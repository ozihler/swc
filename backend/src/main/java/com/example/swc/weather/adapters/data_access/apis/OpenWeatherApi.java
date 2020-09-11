package com.example.swc.weather.adapters.data_access.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.lang.String.*;

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

    public OpenWeatherContentDto getCurrentWeather(float latitude, float longitude) throws IOException {
        String uri = format("%s?appid=%s&lat=%s&lon=%s&units=metric", baseUrl, apiKey, latitude, longitude);

        String response = Request.Get(uri)
                .execute()
                .returnContent()
                .toString();

        return new ObjectMapper().readValue(response, OpenWeatherContentDto.class);
    }

}
