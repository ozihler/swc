package com.example.swc.asteroids.domain;

public class Asteroid {
    private Id id;
    private Name name;
    private MissDistances missDistances;
    private KineticEnergy kineticEnergy;

    public Asteroid(Id id, Name name, MissDistances missDistances, KineticEnergy kineticEnergy) {
        this.id = id;
        this.name = name;
        this.missDistances = missDistances;
        this.kineticEnergy = kineticEnergy;
    }

    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public MissDistances getMissDistances() {
        return missDistances;
    }

    public KineticEnergy getKineticEnergy() {
        return kineticEnergy;
    }
}
