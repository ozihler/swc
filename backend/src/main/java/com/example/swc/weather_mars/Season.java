package com.example.swc.weather_mars;

public enum Season {
    Spring, Summer, Fall, Winter;

    public static Season forName(String seasonName) {
        return switch (seasonName.toLowerCase()) {
            case "spring" -> Spring;
            case "summer" -> Summer;
            case "fall" -> Fall;
            case "winter" -> Winter;
            default -> throw new RuntimeException("Could not find Season " + seasonName);
        };
    }
}
