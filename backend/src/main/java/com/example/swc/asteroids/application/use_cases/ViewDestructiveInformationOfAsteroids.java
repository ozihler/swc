package com.example.swc.asteroids.application.use_cases;

import com.example.swc.asteroids.adapters.data_access.AsteroidsRepository;
import com.example.swc.asteroids.adapters.presentation.RestAsteroidPresenter;
import com.example.swc.asteroids.domain.Asteroids;
import com.example.swc.asteroids.domain.RetrievalDate;

public class ViewDestructiveInformationOfAsteroids {
    private AsteroidsRepository asteroidsRepository;

    public ViewDestructiveInformationOfAsteroids(AsteroidsRepository asteroidsRepository) {
        this.asteroidsRepository = asteroidsRepository;
    }

    public void invokeWith(boolean useTestData, RetrievalDate startDate, RetrievalDate endDate, RestAsteroidPresenter output) {
        Asteroids asteroids = this.asteroidsRepository.fetchAsteroids(startDate, endDate, useTestData);
        output.present(asteroids);
    }
}
