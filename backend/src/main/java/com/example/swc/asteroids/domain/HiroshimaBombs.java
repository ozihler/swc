package com.example.swc.asteroids.domain;

public class HiroshimaBombs {
    private int numberOfHiroshimaBombs;

    public HiroshimaBombs(KineticEnergy kineticEnergy) {
        numberOfHiroshimaBombs = Math.round((float) kineticEnergy.getKineticEnergyInTonsOfTNT() / 15000f);
    }

    public float getNumberOfBombs() {
        return (float) numberOfHiroshimaBombs;
    }

    public int getNumberOfDeaths() {
        return ((int) getNumberOfBombs() * 100000);
    }
}
