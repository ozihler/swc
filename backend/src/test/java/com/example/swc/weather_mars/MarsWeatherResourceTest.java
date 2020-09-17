package com.example.swc.weather_mars;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MarsWeatherResourceTest {

    @Test
    void test() throws IOException {
        MarsWeatherResource testee = new MarsWeatherResource(
                "Some Key",
                "https://base.url"
        );
        
        ResponseEntity<MarsWeatherDto> currentMarsWeather = testee.getCurrentMarsWeather();
    }
}