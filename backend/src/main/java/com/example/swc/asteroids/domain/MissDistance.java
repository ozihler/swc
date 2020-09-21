package com.example.swc.asteroids.domain;

import com.example.swc.asteroids.surrounding_systems.MissDistanceDto;

public class MissDistance {
    private final double missDistance;

    public MissDistance(MissDistanceDto miss_distance) {
        this.missDistance = Double.parseDouble(miss_distance.kilometers);
    }

    public double getMissDistanceInKm() {
        return missDistance;
    }
}
