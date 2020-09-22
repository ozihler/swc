package com.example.swc.asteroids.adapters.data_access;

import com.example.swc.asteroids.domain.Asteroids;
import com.example.swc.asteroids.domain.RetrievalDate;

public interface FetchAsteroids {
    Asteroids between(RetrievalDate startDate, RetrievalDate endDate, boolean useTestData);
}
