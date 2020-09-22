package com.example.swc.asteroids.adapters.presentation;

import com.example.swc.asteroids.application.outbound_ports.AsteroidPresenter;
import com.example.swc.asteroids.domain.Asteroid;
import com.example.swc.asteroids.domain.Asteroids;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class RestAsteroidPresenter implements AsteroidPresenter {
    private ResponseEntity<Map<String, List<Map<String, Object>>>> response;

    public RestAsteroidPresenter() {
        
    }

    static int byNumberOfHiroshimaBombs(Map<String, Object> map1, Map<String, Object> map2) {
        return -((Float) map1.get("numberOfHiroshimaBombs")).compareTo((float) map2.get("numberOfHiroshimaBombs"));
    }

    @Override
    public void present(Asteroids asteroids) {
        Map<String, List<Map<String, Object>>> asteroidDtos = toDtos(asteroids);
        this.response = ResponseEntity.ok(asteroidDtos);
    }

    public ResponseEntity<Map<String, List<Map<String, Object>>>> getResponse() {
        return response;
   }

    private Map<String, List<Map<String, Object>>> toDtos(Asteroids asteroids) {
        List<Map<String, Object>> asteroidDetailsDtos = new ArrayList<>();

        for (Asteroid asteroid : asteroids.getAsteroids()) {
            Map<String, Object> asteroidDetails = new TreeMap<>();
            asteroidDetails.put("id", asteroid.getId().toString());
            asteroidDetails.put("name", asteroid.getName().toString());
            asteroidDetails.put("averageMissDistanceInKm", asteroid.getMissDistances().getAverageMissDistanceInKm());
            asteroidDetails.put("averageLunarDistance", asteroid.getMissDistances().getAverageLunarMissingDistance());
            asteroidDetails.put("kineticEnergyInTonsOfTNT", asteroid.getKineticEnergy().getKineticEnergyInTonsOfTNT());
            asteroidDetails.put("magnitude", asteroid.getKineticEnergy().getMagnitude());
            asteroidDetails.put("numberOfHiroshimaBombs", asteroid.getKineticEnergy().getHiroshimaBombs().getNumberOfBombs());
            asteroidDetails.put("numberOfHiroshimaDeaths", asteroid.getKineticEnergy().getHiroshimaBombs().getNumberOfDeaths());

            asteroidDetailsDtos.add(asteroidDetails);
        }

        Map<String, List<Map<String, Object>>> results = new HashMap<>();
        for (Map<String, Object> asteroidDetails : asteroidDetailsDtos) {
            List<Map<String, Object>> asteroidDtos = getAsteroidDtos(results);
            asteroidDtos.add(asteroidDetails);
            results.put("asteroids", asteroidDtos);
        }

        getAsteroidDtos(results).sort(RestAsteroidPresenter::byNumberOfHiroshimaBombs);
        return results;
    }

    private List<Map<String, Object>> getAsteroidDtos(Map<String, List<Map<String, Object>>> results) {
        List<Map<String, Object>> asteroidDtos = results.get("asteroids");
        if (asteroidDtos == null) {
            asteroidDtos = new ArrayList<>();
        }
        return asteroidDtos;
    }
}
