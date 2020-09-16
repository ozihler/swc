package com.example.swc;

import com.example.swc.weather_earth.surrounding_systems.OpenWeatherApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {

    private final OpenWeatherApi openWeatherApi;

    public TestResource(OpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<WelcomeMessage> getHelloWorld() {
        return ResponseEntity.ok(new WelcomeMessage("Hello World"));
    }

    public static class WelcomeMessage {
        private String message;

        public WelcomeMessage(String message) {
            this.message = message;
        }

        public WelcomeMessage() {
        }

        public String getMessage() {
            return message;
        }
    }

}
