package com.example.swc.asteroids.surrounding_systems;

import com.example.swc.common.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;

@Service
public class NewWsApi {
    private final String apiKey;
    private final String baseUrl;

    public NewWsApi(
            @Value("${asteroids.api.key}") String apiKey,
            @Value("${asteroids.api.baseUrl}") String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    // Idea: find all dangerous asteroids (is_potentially_hazardous_asteroid)
    // with a diameter of xy m, velocity xy, etc.

    public AsteroidsDto getAsteroidData(
            String startDate,
            String endDate,
            boolean detailed,
            boolean useTestData) throws IOException {
        if (useTestData) {
            return testData();
        }

        String uri = format("%s?start_date=%s&end_date=%s&detailed=%s&api_key=%s", baseUrl, startDate, endDate, detailed, apiKey);

        return new HttpRequest<AsteroidsDto>(uri).get(AsteroidsDto.class);
    }

    private AsteroidsDto testData() throws IOException {
        File backupAsteroids = ResourceUtils.getFile("classpath:backup_data/backup_asteroids.json");
        return new ObjectMapper().readValue(backupAsteroids, AsteroidsDto.class);
    }


}
