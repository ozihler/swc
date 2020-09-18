package com.example.swc.asteroids.surrounding_systems;

import com.example.swc.common.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

@Service
public class NewWsApi {
    private final String apiKey;
    private final String baseUrl;
    private final String testDataLocation;

    public NewWsApi(
            @Value("${asteroids.api.key}") String apiKey,
            @Value("${asteroids.api.baseUrl}") String baseUrl,
            @Value("${asteroids.testDataLocation}") String testDataLocation) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.testDataLocation = testDataLocation;
    }

    // Idea: find all dangerous asteroids (is_potentially_hazardous_asteroid)
    // with a diameter of xy m, velocity xy, etc.

    public AsteroidsApiDataDto getAsteroidData(
            Date startDate,
            Date endDate,
            boolean detailed,
            boolean useTestData) throws IOException {
        if (useTestData) {
            return testData();
        }

        String startDateAsString = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        String endDateAsString = new SimpleDateFormat("yyyy-MM-dd").format(endDate);

        String uri = format("%s?start_date=%s&end_date=%s&detailed=%s&api_key=%s", baseUrl, startDateAsString, endDateAsString, detailed, apiKey);

        try {
            return new HttpRequest<AsteroidsApiDataDto>(uri).get(AsteroidsApiDataDto.class);
        } catch (IOException e) {
            return testData();
        }
    }

    protected AsteroidsApiDataDto testData() throws IOException {
        File backupAsteroids = ResourceUtils.getFile(testDataLocation);
        return new ObjectMapper().readValue(backupAsteroids, AsteroidsApiDataDto.class);
    }


}
