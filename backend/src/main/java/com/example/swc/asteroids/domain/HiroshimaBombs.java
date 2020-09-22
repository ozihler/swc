package com.example.swc.asteroids.domain;

public class HiroshimaBombs {
    private int numberOfHiroshimaBombs;

    public HiroshimaBombs(float kineticEnergyInTonsOfTNT) {
        numberOfHiroshimaBombs = Math.round(kineticEnergyInTonsOfTNT / 15000f);
    }

    public float getNumberOfBombs() {
        return (float) numberOfHiroshimaBombs;
    }

    public int getNumberOfDeaths() {
        return ((int) getNumberOfBombs() * 100000);
    }
}
