package com.example.swc.asteroids.application.use_cases.port;

import com.example.swc.asteroids.adapters.presentation.AsteroidPresenter;
import com.example.swc.asteroids.domain.RetrievalDate;

public interface ViewDestructiveInformationOfAsteroids {
    void invokeWith(boolean useTestData, RetrievalDate startDate, RetrievalDate endDate, AsteroidPresenter output);
}
