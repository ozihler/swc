package com.example.swc.asteroids.application.use_cases.port;

import com.example.swc.asteroids.application.outbound_ports.AsteroidPresenter;
import com.example.swc.asteroids.domain.RetrievalDate;

public interface ViewDestructiveInformationOfAsteroids {
    void invokeWith(RetrievalDate startDate, RetrievalDate endDate, boolean useTestData, AsteroidPresenter output);
}
