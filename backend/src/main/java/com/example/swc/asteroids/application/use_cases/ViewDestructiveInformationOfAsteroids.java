package com.example.swc.asteroids.application.use_cases;

import com.example.swc.asteroids.adapters.data_access.FetchAsteroids;
import com.example.swc.asteroids.adapters.presentation.RestAsteroidPresenter;
import com.example.swc.asteroids.domain.Asteroids;
import com.example.swc.asteroids.domain.RetrievalDate;

public class ViewDestructiveInformationOfAsteroids {
    private FetchAsteroids fetchAsteroids;

    public ViewDestructiveInformationOfAsteroids(FetchAsteroids fetchAsteroids) {
        this.fetchAsteroids = fetchAsteroids;
    }

    public void invokeWith(boolean useTestData, RetrievalDate startDate, RetrievalDate endDate, RestAsteroidPresenter output) {
        Asteroids asteroids = this.fetchAsteroids.between(startDate, endDate, useTestData);
        output.present(asteroids);
    }
}
