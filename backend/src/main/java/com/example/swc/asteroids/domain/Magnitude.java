package com.example.swc.asteroids.domain;

public class Magnitude {
    public double kineticEnergyInTonsOfTNT;

    public Magnitude(double kineticEnergyInTonsOfTNT) {
        this.kineticEnergyInTonsOfTNT = kineticEnergyInTonsOfTNT;
    }

    public String getValue() {
        if (isBelowTons()) {
            return "BELOW_TONS";
        } else if (isTons()) {
            return "TONS";
        } else if (isKiloTons()) {
            return "KILO_TONS";
        } else if (isMegaTons()) {
            return "MEGA_TONS";
        } else {
            return "ABOVE_MEGA_TONS";
        }
    }

    private boolean isMegaTons() {
        return kineticEnergyInTonsOfTNT / 1000000 >= 1.0 && kineticEnergyInTonsOfTNT / 1000000000 < 1.0;
    }

    private boolean isKiloTons() {
        return kineticEnergyInTonsOfTNT / 1000 >= 1.0 && kineticEnergyInTonsOfTNT / 1000000 < 1.0;
    }

    private boolean isTons() {
        return kineticEnergyInTonsOfTNT / 1000 < 1.0 && kineticEnergyInTonsOfTNT >= 1.0;
    }

    private boolean isBelowTons() {
        return kineticEnergyInTonsOfTNT < 1.0;
    }
}
