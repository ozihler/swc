package com.example.swc.weather_mars;

import com.example.swc.common.Http;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MarsWeatherResource {

    private final String api_key;
    private final String baseUrl;

    public MarsWeatherResource(
            @Value("${mars.weather.api.key}") String api_key,
            @Value("${mars.weather.api.baseUrl}") String baseUrl
    ) {
        this.api_key = api_key;
        this.baseUrl = baseUrl;
    }

    @GetMapping("/api/mars-weather")
    public ResponseEntity getCurrentMarsWeather() throws IOException {
        String url = String.format("%s?api_key=%s&feedtype=json&ver=1.0", baseUrl, api_key);
        System.err.println(url);
        Object object = Http.get(url, Object.class);
        return ResponseEntity.ok(object);
    }
}
