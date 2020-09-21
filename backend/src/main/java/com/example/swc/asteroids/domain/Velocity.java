package com.example.swc.asteroids.domain;

public class Velocity {
    private final double kilometersPerSecond;

    public Velocity(double kilometersPerSecond) {
        this.kilometersPerSecond = kilometersPerSecond;
    }

    public double getValue() {
        return kilometersPerSecond;
    }
}
