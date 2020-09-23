package com.example.swc.weather_mars;

import com.example.swc.common.HttpRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class MarsWeatherResource {

    private final String api_key;
    private final String baseUrl;

    /**
     * https://api.nasa.gov/assets/insight/InSight%20Weather%20API%20Documentation.pdf
     * AT.av = Average Temperature in Fahrenheit
     * Season = winter, spring, summer, fall
     *
     * @param api_key
     * @param baseUrl
     */
    public MarsWeatherResource(
            @Value("${mars.weather.api.key}") String api_key,
            @Value("${mars.weather.api.baseUrl}") String baseUrl
    ) {
        this.api_key = api_key;
        this.baseUrl = baseUrl;
    }

    @GetMapping("/api/mars-weather")
    public ResponseEntity<MarsWeatherDto> getCurrentMarsWeather() throws IOException {
        String url = String.format("%s?api_key=%s&feedtype=json&ver=1.0", baseUrl, api_key);

        Map<String, Object> object = new HttpRequest<Map<String, Object>>(url).getAsType(new TypeReference<>() {
        });


        MarsWeatherDto marsWeather = new MarsWeatherDtoFactory().createDtoFrom(object);

        return ResponseEntity.ok(marsWeather);
    }

}
