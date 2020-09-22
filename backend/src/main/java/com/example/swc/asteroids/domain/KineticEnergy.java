package com.example.swc.asteroids.domain;

public class KineticEnergy {
    private Measures measures;
    private Velocities velocities;
    private Magnitude magnitude;
    private HiroshimaBombs hiroshimaBombs;

    public KineticEnergy(Measures measures, Velocities velocities) {
        this.measures = measures;
        this.velocities = velocities;
        this.magnitude = new Magnitude(getKineticEnergyInTonsOfTNT());
        this.hiroshimaBombs = new HiroshimaBombs((float) this.getKineticEnergyInTonsOfTNT());
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

    public Measures getMeasures() {
        return measures;
    }

    public Velocities getVelocities() {
        return velocities;
    }

    public Magnitude getMagnitude() {
        return magnitude;
    }

    public HiroshimaBombs getHiroshimaBombs() {
        return hiroshimaBombs;
    }
}
