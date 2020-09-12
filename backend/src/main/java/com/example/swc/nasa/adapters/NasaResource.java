package com.example.swc.nasa.adapters;

import com.example.swc.nasa.surrounding_systems.AsteroidsDto;
import com.example.swc.nasa.surrounding_systems.NasaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class NasaResource {

    private NasaApi nasaApi;

    @Autowired
    public NasaResource(NasaApi nasaApi) {
        this.nasaApi = nasaApi;
    }

    @GetMapping("/nasa/asteroids")
    public ResponseEntity<AsteroidsDto> getAsteroids(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("isDetailed") boolean isDetailed
    ) throws IOException {
        AsteroidsDto data = this.nasaApi.getAsteroidData(startDate, endDate, isDetailed);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/nasa/asteroids/missingDistance")
    public ResponseEntity<Object> getMissingDistance() {

        return ResponseEntity.ok("Hello World");
    }
}
