package com.example.swc.asteroids.domain;

import java.util.List;

public class MissDistances {
    private static final int LUNAR_DISTANCE_IN_KM = 384400;

    public List<MissDistance> missDistances;

    public MissDistances(List<MissDistance> missDistances) {
        this.missDistances = missDistances;
    }

    public double getAverageMissDistanceInKm() {
        return this.missDistances.stream()
                .mapToDouble(MissDistance::getMissDistanceInKm)
                .average()
                .orElse(0.0);
    }

    public double getAverageLunarMissingDistance() {
        return getAverageMissDistanceInKm() / LUNAR_DISTANCE_IN_KM;
    }
}
