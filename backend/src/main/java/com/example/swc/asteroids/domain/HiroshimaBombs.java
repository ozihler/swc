package com.example.swc.asteroids.domain;

public class HiroshimaBombs {
    private KineticEnergy kineticEnergy;

    public HiroshimaBombs(KineticEnergy kineticEnergy) {
        this.kineticEnergy = kineticEnergy;
    }

    public int getNumberOfBombs() {
        return Math.round((float) kineticEnergy.getKineticEnergyInTonsOfTNT() / 15000f);
    }

    public int getNumberOfDeaths() {
        return getNumberOfBombs() * 100000;
    }
}
