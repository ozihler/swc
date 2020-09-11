package com.example.swc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {

    public static class WelcomeMessage{
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

    @GetMapping("/hello-world")
    public ResponseEntity<WelcomeMessage> getHelloWorld() {
        return ResponseEntity.ok(new WelcomeMessage("Hello World"));
    }
}
