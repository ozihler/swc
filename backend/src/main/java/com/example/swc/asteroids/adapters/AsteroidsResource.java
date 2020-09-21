package com.example.swc.asteroids.adapters;

import com.example.swc.asteroids.domain.*;
import com.example.swc.asteroids.surrounding_systems.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;

@RestController
public class AsteroidsResource {

    private final NewWsApi newWsApi;

    @Autowired
    public AsteroidsResource(NewWsApi newWsApi) {
        this.newWsApi = newWsApi;
    }

    @GetMapping("/api/asteroids")
    public ResponseEntity<AsteroidsApiDataDto> getAsteroids(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("isDetailed") boolean isDetailed
    ) throws IOException {
        Date start = new RetrievalDate(startDate).toDate();
        Date end = new RetrievalDate(endDate).toDate();
        AsteroidsApiDataDto data = this.newWsApi.getAsteroidData(start, end, isDetailed, false);
        return ResponseEntity.ok(data);
    }

    /**
     * https://www.real-world-physics-problems.com/asteroid-impact.html#:~:text=For%20example%2C%20consider%20an%20asteroid,2.8%C3%971020%20Joules.
     * <p>
     * Density * Volume (m3) = Mass (kg)
     * Volume = (4/3)*PI*R^3
     * Asteroid Densities: 8g/cm3 (Iron), 3g/cm3 (Chondrits) => Avg 4g/cm3
     * g/cm3 *1000 == kg/m3 ==> 4000 kg/m3
     *
     * @param startDate
     * @param endDate
     * @param useTestData
     * @return
     */
    @GetMapping("/api/asteroids/kineticEnergy")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getDestructiveInformationOfAsteroids(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("useTestData") boolean useTestData) { // Long method

        try { // Deeply-nested control flow
            double density = 4000;

            Map<String, List<Map<String, Object>>> results = new HashMap<>(); // Primitive Obsession

            AsteroidsApiDataDto dataFromApi = this.newWsApi.getAsteroidData(new RetrievalDate(startDate).toDate(), new RetrievalDate(endDate).toDate(), false, useTestData);

            for (Map.Entry<String, List<NearEarthObjectDto>> asteroidsPerDate : dataFromApi.near_earth_objects.entrySet()) {
                for (NearEarthObjectDto asteroid : asteroidsPerDate.getValue()) {
                    Map<String, Object> asteroidDetails = new TreeMap<>();
                    asteroidDetails.put("id", asteroid.id);
                    asteroidDetails.put("name", asteroid.name);

                    MissDistances missDistances = toMissDistances(asteroid.close_approach_data);

                    asteroidDetails.put("averageMissDistanceInKm", missDistances.getAverageMissDistanceInKm());
                    asteroidDetails.put("averageLunarDistance", missDistances.getAverageLunarMissingDistance());

                    DiameterDto meters = asteroid.estimated_diameter.meters;

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

                    Measures measures = new Measures(meters.estimated_diameter_min, meters.estimated_diameter_max);

                    double kineticEnergyInJoules = 0.5 * measures.massInKg() * averageVelocityInMPerSecond * averageVelocityInMPerSecond;
                    double kineticEnergyInTonsOfTNT = kineticEnergyInJoules * 0.00000000024;         // 1 joule = 0.00000000024 tons of TNT
                    asteroidDetails.put("kineticEnergyInTonsOfTNT", kineticEnergyInTonsOfTNT);

                    addMagnitude(asteroidDetails, kineticEnergyInTonsOfTNT);

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

            return ResponseEntity.ok(results);

        } catch (IOException i) {
            throw new RuntimeException(i);
        }
    }

    private MissDistances toMissDistances(List<CloseApproachDataDto> closeApproachData) {
        return new MissDistances(
                closeApproachData.stream()
                        .map(a -> a.miss_distance)
                        .map(MissDistance::new)
                        .collect(toList()));
    }

    public static void addMagnitude(Map<String, Object> asteroidDetails, double kineticEnergyInTonsOfTNT) {
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
    }
}
