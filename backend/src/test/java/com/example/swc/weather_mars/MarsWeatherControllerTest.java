package com.example.swc.weather_mars;

import com.example.swc.weather_mars.adapters.apis.MarsWeatherApi;
import com.example.swc.weather_mars.adapters.controllers.MarsWeatherController;
import com.example.swc.weather_mars.adapters.presenters.MarsWeatherDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MarsWeatherControllerTest {

    @Test
    void test() throws IOException {
        MarsWeatherController testee = new MarsWeatherController(
                new MarsWeatherApi("SomeKey", "https://baseurl")
        );

        ResponseEntity<MarsWeatherDto> currentMarsWeather = testee.viewCurrentMarsWeather();

        assertEquals(HttpStatus.OK, currentMarsWeather.getStatusCode());
    }
}