package com.example.swc.asteroids.application.outbound_ports;

import com.example.swc.asteroids.domain.Asteroids;
import com.example.swc.asteroids.domain.RetrievalDate;

public interface FetchAsteroids {
    Asteroids between(RetrievalDate startDate, RetrievalDate endDate, boolean useTestData);
}
