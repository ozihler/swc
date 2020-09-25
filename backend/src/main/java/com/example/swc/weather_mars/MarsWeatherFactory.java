package com.example.swc.weather_mars;

import com.example.swc.domain.Latitude;
import com.example.swc.domain.Location;
import com.example.swc.domain.Longitude;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class MarsWeatherFactory {
    public static final float INSIGHT_LANDING_SITE_LATITUDE = 4.5024f;
    public static final float INSIGHT_LANDING_SITE_LONGITUDE = 135.6234f;

    static List<Double> temperaturesInFahrenheitFrom(Map<String, Object> object) {
        return temperatureDataPointsIn(object)
                .stream()
                .mapToDouble(MarsWeatherFactory::toTemperature)
                .boxed()
                .collect(toList());
    }

    private static Double toTemperature(Map<String, Object> value) {
        return (Double) ((Map<String, Object>) value.get("AT")).get("av");
    }

    static String seasonFrom(Map<String, Object> object) {
        return temperatureDataPointsIn(object)
                .stream()
                .findFirst()
                .map(MarsWeatherFactory::toSeason)
                .orElse("");
    }

    private static String toSeason(Map<String, Object> value) {
        return (String) value.get("Season");
    }

    private static List<Map<String, Object>> temperatureDataPointsIn(Map<String, Object> object) {
        return object.entrySet()
                .stream()
                .filter(o -> hasTemperature(o.getKey()))
                .map(o -> (Map<String, Object>) o.getValue())
                .collect(toList());
    }

    private static boolean hasTemperature(String dataPoint) {
        try {
            Integer.parseInt(dataPoint);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public MarsWeather createDomainObjectFrom(Map<String, Object> object) {
        String seasonName = seasonFrom(object);
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);

        Location location = new Location(new Latitude(INSIGHT_LANDING_SITE_LATITUDE), new Longitude(INSIGHT_LANDING_SITE_LONGITUDE));
        Season season = Season.forName(seasonName);
        FahrenheitTemperatures fahrenheitTemperatures = new FahrenheitTemperatures(temperaturesInFahrenheit);
        return new MarsWeather(location, season, fahrenheitTemperatures);
    }
}
