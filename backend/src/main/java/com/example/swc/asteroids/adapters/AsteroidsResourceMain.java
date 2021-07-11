package com.example.swc.asteroids.adapters;

import com.example.swc.asteroids.surrounding_systems.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
public class AsteroidsResourceMain {


    public static void main(String[] args) { // Long method

        try { // Deeply-nested control flow
            // https://www.real-world-physics-problems.com/asteroid-impact.html#:~:text=For%20example%2C%20consider%20an%20asteroid,2.8%C3%971020%20Joules.

            // Density * Volume (m3) = Mass (kg)
            // Volume = (4/3)*PI*R^3
            // Asteroid Densities: 8g/cm3 (Iron), 3g/cm3 (Chondrits) => Avg 4g/cm3
            // g/cm3 *1000 == kg/m3 ==> 4000 kg/m3

            Map<String, List<Map<String, Object>>> results = new HashMap<>(); // Primitive Obsession

            File backupAsteroids = ResourceUtils.getFile("classpath:test_data/test_asteroids.json");
            AsteroidsApiDataDto dataFromApi = new ObjectMapper().readValue(backupAsteroids, AsteroidsApiDataDto.class);

            for (Map.Entry<String, List<NearEarthObjectDto>> asteroidsPerDate : dataFromApi.near_earth_objects.entrySet()) {
                for (NearEarthObjectDto asteroid : asteroidsPerDate.getValue()) {
                    Map<String, Object> asteroidDetails = new TreeMap<>();
                    asteroidDetails.put("id", asteroid.id);
                    asteroidDetails.put("name", asteroid.name);

                    double result = 0;
                    for (CloseApproachDataDto a : asteroid.close_approach_data) {
                        result += Double.parseDouble(a.miss_distance.kilometers);
                    }
                    double avgDist = asteroid.close_approach_data.size() > 0 ? result / asteroid.close_approach_data.size() : 0.0;

                    asteroidDetails.put("averageMissDistanceInKm", avgDist);
                    asteroidDetails.put("averageLunarDistance", (avgDist / 384400)); // Magic Number

                    double minDiam = asteroid.estimated_diameter.meters.estimated_diameter_min;
                    double maxDiam = asteroid.estimated_diameter.meters.estimated_diameter_max;
                    double massInKg = (4 / 3d) * Math.PI * ((minDiam + maxDiam) / 2d / 2) * (double) 4000;

                    double sum = 0;
                    long count = 0;
                    for (CloseApproachDataDto d : asteroid.close_approach_data) {
                        sum += Double.parseDouble(d.relative_velocity.kilometers_per_second);
                        count++;
                    }

                    double averageVelocityInMPerSecond = 0;
                    if (count > 0) {
                        averageVelocityInMPerSecond = (sum / count) * 1000;
                    }

                    double kineticEnergyInJoules = 0.5 * massInKg * averageVelocityInMPerSecond * averageVelocityInMPerSecond;
                    double kineticEnergyInTonsOfTNT = kineticEnergyInJoules * 0.00000000024;         // 1 joule = 0.00000000024 tons of TNT
                    asteroidDetails.put("kineticEnergyInTonsOfTNT", kineticEnergyInTonsOfTNT);

                    if (kineticEnergyInTonsOfTNT < 1.0) {
                        asteroidDetails.put("magnitude", "BELOW_TONS");
                    } else if (kineticEnergyInTonsOfTNT / 1000 < 1.0 && kineticEnergyInTonsOfTNT >= 1.0) {
                        asteroidDetails.put("magnitude", "TONS");
                    } else if (kineticEnergyInTonsOfTNT / 1000 >= 1.0 && kineticEnergyInTonsOfTNT / 1000000 < 1.0) {
                        asteroidDetails.put("magnitude", "KILO_TONS");
                    } else if (kineticEnergyInTonsOfTNT / 1000000 >= 1.0 && kineticEnergyInTonsOfTNT / 1000000000 < 1.0) { // complicated boolean expression
                        asteroidDetails.put("magnitude", "MEGA_TONS");
                    } else {
                        asteroidDetails.put("magnitude", "ABOVE_MEGA_TONS");
                    }

                    float numberOfHiroshimaBombs = (Math.round((float) kineticEnergyInTonsOfTNT / 15000f)); // Magic Number

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

            System.out.println(results);

        } catch (IOException i) {
            throw new RuntimeException(i);
        }
    }

}
