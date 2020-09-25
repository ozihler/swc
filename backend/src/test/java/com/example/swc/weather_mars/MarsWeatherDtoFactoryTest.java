package com.example.swc.weather_mars;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MarsWeatherDtoFactoryTest {

    @Test
    void testSingleDataPoint() {
        var factory = new MarsWeatherDtoFactory();

        var marsWeatherData = new HashMap<String, Object>();
        marsWeatherData.put("1", Map.of("Season", "Summer", "AT", Map.of("av", 100.0)));

        MarsWeather marsWeather = new MarsWeatherFactory().createDomainObjectFrom(marsWeatherData);

        var dto = factory.createDtoFrom(marsWeather);

        assertEquals(37.777, dto.averageTemperatureInCelsius, 0.01);
        assertEquals("Summer", dto.season);
        assertEquals(4.5024f, dto.location.latitude);
        assertEquals(135.6234f, dto.location.longitude);
    }

    @Test
    void testMultipleTemperaturesTakesAverage() {
        var factory = new MarsWeatherDtoFactory();

        var marsWeatherData = new HashMap<String, Object>();
        marsWeatherData.put("1", Map.of("Season", "Summer", "AT", Map.of("av", 100.0)));
        marsWeatherData.put("2", Map.of("Season", "Summer", "AT", Map.of("av", 200.0)));
        marsWeatherData.put("3", Map.of("Season", "Summer", "AT", Map.of("av", 300.0)));

        MarsWeather marsWeather = new MarsWeatherFactory().createDomainObjectFrom(marsWeatherData);

        var dto = factory.createDtoFrom(marsWeather);

        assertEquals(93.333, dto.averageTemperatureInCelsius, 0.01);
    }

    @Test
    void testDifferentSeasonTakesFirstSeason() {
        var factory = new MarsWeatherDtoFactory();

        var marsWeatherData = new LinkedHashMap<String, Object>();
        marsWeatherData.put("1", Map.of("Season", "Summer", "AT", Map.of("av", 100.0)));
        marsWeatherData.put("2", Map.of("Season", "Winter", "AT", Map.of("av", 200.0)));
        marsWeatherData.put("3", Map.of("Season", "Spring", "AT", Map.of("av", 300.0)));

        MarsWeather marsWeather = new MarsWeatherFactory().createDomainObjectFrom(marsWeatherData);

        var dto = factory.createDtoFrom(marsWeather);

        assertEquals("Summer", dto.season);
    }

    @Test
    void testSkipItemWithoutNumberIdButString() {
        var factory = new MarsWeatherDtoFactory();

        var marsWeatherData = new LinkedHashMap<String, Object>();
        marsWeatherData.put("Some other data", Map.of("Other key", "Other value"));
        marsWeatherData.put("1", Map.of("Season", "Summer", "AT", Map.of("av", 100.0)));
        marsWeatherData.put("2", Map.of("Season", "Spring", "AT", Map.of("av", 300.0)));

        MarsWeather marsWeather = new MarsWeatherFactory().createDomainObjectFrom(marsWeatherData);

        var dto = factory.createDtoFrom(marsWeather);

        assertEquals("Summer", dto.season);
        assertEquals(93.333, dto.averageTemperatureInCelsius, 0.01);
    }

}