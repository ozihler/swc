package com.example.swc.asteroids.domain;

import java.util.List;

public class Asteroids {
    private List<Asteroid> asteroids;

    public Asteroids(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }
}
