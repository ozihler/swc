package com.example.swc.asteroids.domain;

public class Measures {
    private static double AVERAGE_ASTEROID_DENSITY = 4000;

    public final double estimatedMinimalDiameter;
    public final double estimatedMaximalDiameter;

    public Measures(double estimatedMinimalDiameter, double estimatedMaximalDiameter) {
        this.estimatedMinimalDiameter = estimatedMinimalDiameter;
        this.estimatedMaximalDiameter = estimatedMaximalDiameter;
    }

    public double averageInMeters() {
        return (estimatedMaximalDiameter + estimatedMinimalDiameter) / 2d;
    }

    public double radiusInMeters() {
        return averageInMeters() / 2;
    }

    public double volumeInCubicMeters() {
        return (4 / 3d) * Math.PI * radiusInMeters();
    }

    public double massInKg() {
        return volumeInCubicMeters() * AVERAGE_ASTEROID_DENSITY;
    }
}
