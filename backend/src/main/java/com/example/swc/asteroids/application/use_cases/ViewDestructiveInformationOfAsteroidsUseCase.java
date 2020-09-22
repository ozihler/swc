package com.example.swc.asteroids.application.use_cases;

import com.example.swc.asteroids.application.outbound_ports.FetchAsteroids;
import com.example.swc.asteroids.application.outbound_ports.AsteroidPresenter;
import com.example.swc.asteroids.application.use_cases.port.ViewDestructiveInformationOfAsteroids;
import com.example.swc.asteroids.domain.Asteroids;
import com.example.swc.asteroids.domain.RetrievalDate;

public class ViewDestructiveInformationOfAsteroidsUseCase implements ViewDestructiveInformationOfAsteroids {
    private FetchAsteroids fetchAsteroids;

    public ViewDestructiveInformationOfAsteroidsUseCase(FetchAsteroids fetchAsteroids) {
        this.fetchAsteroids = fetchAsteroids;
    }

    @Override
    public void invokeWith(RetrievalDate startDate, RetrievalDate endDate, boolean useTestData, AsteroidPresenter output) {
        Asteroids asteroids = this.fetchAsteroids.between(startDate, endDate, useTestData);
        output.present(asteroids);
    }
}
