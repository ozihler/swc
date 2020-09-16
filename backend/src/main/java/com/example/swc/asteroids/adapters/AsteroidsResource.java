package com.example.swc.asteroids.adapters;

import com.example.swc.asteroids.surrounding_systems.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
public class AsteroidsResource {

    private final NewWsApi newWsApi;

    @Autowired
    public AsteroidsResource(NewWsApi newWsApi) {
        this.newWsApi = newWsApi;
    }

    @GetMapping("/api/asteroids")
    public ResponseEntity<AsteroidsDto> getAsteroids(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("isDetailed") boolean isDetailed
    ) throws IOException {
        AsteroidsDto data = this.newWsApi.getAsteroidData(startDate, endDate, isDetailed, false);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/api/asteroids/kineticEnergy")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getMissingDistance(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("useTestData") boolean useTestData) throws IOException {

        // https://www.real-world-physics-problems.com/asteroid-impact.html#:~:text=For%20example%2C%20consider%20an%20asteroid,2.8%C3%971020%20Joules.

        double jouleToTonOfTnt = 0.00000000024; // 1 joule = 0.00000000024 tons of TNT
        // Density * Volume (m3) = Mass (kg)
        // Volume = (4/3)*PI*R^3
        // Asteroid Densities: 8g/cm3 (Iron), 3g/cm3 (Chondrits) => Avg 4g/cm3
        // g/cm3 *1000 == kg/m3 ==> 4000 kg/m3
        double density = 4000;

        Map<String, List<Map<String, Object>>> results = new HashMap<>();

        AsteroidsDto data = this.newWsApi.getAsteroidData(startDate, endDate, false, useTestData);
        for (Map.Entry<String, List<NearEarthObjectDto>> asteroidsPerDate : data.near_earth_objects.entrySet()) {
            for (NearEarthObjectDto asteroid : asteroidsPerDate.getValue()) {
                Map<String, Object> asteroidDetails = new TreeMap<>();
                asteroidDetails.put("id", asteroid.id);
                asteroidDetails.put("name", asteroid.name);
                double averageMissDistanceInKm = asteroid.close_approach_data.stream()
                        .map(a -> a.miss_distance)
                        .map(a -> a.kilometers)
                        .mapToDouble(Double::parseDouble)
                        .average()
                        .getAsDouble();

                asteroidDetails.put("averageMissDistanceInKm", averageMissDistanceInKm);
                asteroidDetails.put("averageLunarDistance", (averageMissDistanceInKm / 384400));

                DiameterDto meters = asteroid.estimated_diameter.meters;
                double averageDiameterInMeters = (meters.estimated_diameter_min + meters.estimated_diameter_max) / 2d;
                double radius = averageDiameterInMeters / 2;
                double volumeInCubicMeters = (4 / 3d) * Math.PI * radius;
                double massInKg = volumeInCubicMeters * density;

                double sum = 0;
                long count = 0;
                for (CloseApproachDataDto d : asteroid.close_approach_data) {
                    sum += Double.parseDouble(d.relative_velocity.kilometers_per_second);
                    count++;
                }

                double averageVelocityInMPerSecond;
                if (count > 0) {
                    averageVelocityInMPerSecond = sum / count * 1000;
                } else {
                    averageVelocityInMPerSecond = 0;
                }

                double kineticEnergyInJoules = 0.5 * massInKg * averageVelocityInMPerSecond * averageVelocityInMPerSecond;
                double kineticEnergyInTonsOfTNT = kineticEnergyInJoules * jouleToTonOfTnt;
                asteroidDetails.put("kineticEnergyInTonsOfTNT", kineticEnergyInTonsOfTNT);

                if (kineticEnergyInTonsOfTNT < 1.0) {
                    asteroidDetails.put("magnitude", "BELOW_TONS");
                } else if (kineticEnergyInTonsOfTNT / 1000 < 1.0 && kineticEnergyInTonsOfTNT > 1.0) {
                    asteroidDetails.put("magnitude", "TONS");
                } else if (kineticEnergyInTonsOfTNT / 1000 >= 1.0 && kineticEnergyInTonsOfTNT / 1000000 < 1.0) {
                    asteroidDetails.put("magnitude", "KILO_TONS");
                } else if (kineticEnergyInTonsOfTNT / 1000000 >= 1.0 && kineticEnergyInTonsOfTNT / 1000000000 < 1.0) {
                    asteroidDetails.put("magnitude", "MEGA_TONS");
                } else {
                    asteroidDetails.put("magnitude", "ABOVE_MEGA_TONS");
                }

                float numberOfHiroshimaBombs = (Math.round((float) kineticEnergyInTonsOfTNT / 15000f));
                if (numberOfHiroshimaBombs < 1.0) {
                    continue;
                }

                asteroidDetails.put("numberOfHiroshimaBombs", numberOfHiroshimaBombs);

                int numberOfHiroshimaDeaths = ((int) numberOfHiroshimaBombs) * 100000;
                asteroidDetails.put("numberOfHiroshimaDeaths", numberOfHiroshimaDeaths);

                List<Map<String, Object>> asteroids = results.get("asteroids");
                if (asteroids == null) {
                    asteroids = new ArrayList<>();
                }
                asteroids.add(asteroidDetails);

                asteroids.sort((map1, map2) -> -((Float) map1.get("numberOfHiroshimaBombs")).compareTo((float) map2.get("numberOfHiroshimaBombs")));
                results.put("asteroids", asteroids);
            }
        }
        TreeMap<String, Object> statistics = new TreeMap<>();

        double averageNrOfHiroshimaBombs = results.values()
                .stream()
                .flatMap(Collection::stream)
                .map(m -> m.get("numberOfHiroshimaBombs"))
                .mapToDouble(d -> (float) d)
                .average()
                .getAsDouble();

        statistics.put("averageNrOfHiroshimaBombs", averageNrOfHiroshimaBombs);

        double numberOfHiroshimaBombs = results.values()
                .stream()
                .flatMap(Collection::stream)
                .map(m -> m.get("numberOfHiroshimaBombs"))
                .mapToDouble(d -> (float) d)
                .map(n -> Math.pow(n - averageNrOfHiroshimaBombs, 2))
                .sum();

        double standardDeviation = Math.sqrt(numberOfHiroshimaBombs / results.get("asteroids").size());

        statistics.put("sdInNrOfHiroshimaBombs", standardDeviation);

        results.put("statistics", List.of(statistics));
        return ResponseEntity.ok(results);
    }
}
