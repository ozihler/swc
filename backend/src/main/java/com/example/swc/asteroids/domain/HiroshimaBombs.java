package com.example.swc.asteroids.domain;

public class HiroshimaBombs {
    private int numberOfHiroshimaBombs;

    public HiroshimaBombs(KineticEnergy kineticEnergy) {
        numberOfHiroshimaBombs = Math.round((float) kineticEnergy.getKineticEnergyInTonsOfTNT() / 15000f);
    }

    public int getNumberOfBombs() {
        return numberOfHiroshimaBombs;
    }

    public int getNumberOfDeaths() {
        return getNumberOfBombs() * 100000;
    }
}
