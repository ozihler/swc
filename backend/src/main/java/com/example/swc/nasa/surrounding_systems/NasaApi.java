package com.example.swc.nasa.surrounding_systems;

import com.example.swc.common.Http;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.lang.String.format;

@Service
public class NasaApi {
    private final String apiKey;
    private final String baseUrl;

    public NasaApi(
            @Value("${nasa.api.key}") String apiKey,
            @Value("${nasa.api.baseUrl}") String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    // Idea: find all dangerous asteroids (is_potentially_hazardous_asteroid)
    // with a diameter of xy m, velocity xy, etc.

    public AsteroidsDto getAsteroidData(
            String startDate,
            String endDate,
            boolean detailed) throws IOException {
        String uri = format("%s?start_date=%s&end_date=%s&detailed=%s&api_key=%s", baseUrl, startDate, endDate, detailed, apiKey);

        return Http.get(uri, AsteroidsDto.class);
    }


}
