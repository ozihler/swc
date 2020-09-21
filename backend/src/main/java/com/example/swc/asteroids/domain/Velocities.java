package com.example.swc.asteroids.domain;

import java.util.List;

public class Velocities {
    public List<Velocity> velocities;

    public Velocities(List<Velocity> velocities) {
        this.velocities = velocities;
    }

    public double averageVelocityInMetersPerSecond() {
        return this.velocities.stream()
                .mapToDouble(Velocity::getValue)
                .average()
                .orElse(0.0)
                * 1000;
    }
}
