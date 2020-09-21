package com.example.swc.asteroids.domain;

public class KineticEnergy {
    private Measures measures;
    private Velocities velocities;

    public KineticEnergy(Measures measures, Velocities velocities) {
        this.measures = measures;
        this.velocities = velocities;
    }

    public double getKineticEnergyInJoules() {
        return 0.5 * measures.massInKg()
                * velocities.averageVelocityInMetersPerSecond()
                * velocities.averageVelocityInMetersPerSecond();
    }

    public double getKineticEnergyInTonsOfTNT() {
        // 1 joule = 0.00000000024 tons of TNT
        return getKineticEnergyInJoules() * 0.00000000024;
    }
}
