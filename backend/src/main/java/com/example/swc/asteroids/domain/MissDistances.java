package com.example.swc.asteroids.domain;

import java.util.List;

public class MissDistances {
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

}
