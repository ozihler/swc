package com.example.swc.asteroids.domain;

import java.util.List;

public class CloseApproachData {
    private List<MissDistance> missDistances;

    public void addMissDistance(MissDistance missDistance) {
        this.missDistances.add(missDistance);
    }
}
