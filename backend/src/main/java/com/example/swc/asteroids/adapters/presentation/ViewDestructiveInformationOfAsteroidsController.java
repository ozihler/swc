package com.example.swc.asteroids.adapters.presentation;

import com.example.swc.asteroids.adapters.data_access.FetchAsteroids;
import com.example.swc.asteroids.application.use_cases.ViewDestructiveInformationOfAsteroidsUseCase;
import com.example.swc.asteroids.application.use_cases.port.ViewDestructiveInformationOfAsteroids;
import com.example.swc.asteroids.domain.RetrievalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ViewDestructiveInformationOfAsteroidsController {
    private final ViewDestructiveInformationOfAsteroids useCase;

    @Autowired
    public ViewDestructiveInformationOfAsteroidsController(FetchAsteroids fetchAsteroids) {
        useCase = new ViewDestructiveInformationOfAsteroidsUseCase(fetchAsteroids);
    }

    public ResponseEntity<Map<String, List<Map<String, Object>>>> getDestructiveInformationOfAsteroids(String startDateString, String endDateString, boolean useTestData) {
        RetrievalDate startDate = new RetrievalDate(startDateString);
        RetrievalDate endDate = new RetrievalDate(endDateString);

        RestAsteroidPresenter output = new RestAsteroidPresenter();

        useCase.invokeWith(startDate, endDate, useTestData, output);

        return output.getResponse();
    }
}
