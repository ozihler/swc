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
            Map<String, List<Map<String, Object>>> results = new HashMap<>(); // Primitive Obsession

            AsteroidsApiDataDto dataFromApi = this.newWsApi.getAsteroidData(
                    new RetrievalDate(startDate).toDate(),
                    new RetrievalDate(endDate).toDate(),
                    false,
                    useTestData);

            for (Map.Entry<String, List<NearEarthObjectDto>> asteroidsPerDate : dataFromApi.near_earth_objects.entrySet()) {
                for (NearEarthObjectDto asteroidDto : asteroidsPerDate.getValue()) {

                    Asteroid asteroid = toAsteroid(asteroidDto);

                    Map<String, Object> asteroidDetails = new TreeMap<>();
                    asteroidDetails.put("id", asteroid.getId().toString());
                    asteroidDetails.put("name", asteroid.getName().toString());
                    asteroidDetails.put("averageMissDistanceInKm", asteroid.getMissDistances().getAverageMissDistanceInKm());
                    asteroidDetails.put("averageLunarDistance", asteroid.getMissDistances().getAverageLunarMissingDistance());
                    asteroidDetails.put("kineticEnergyInTonsOfTNT", asteroid.getKineticEnergy().getKineticEnergyInTonsOfTNT());
                    asteroidDetails.put("magnitude", asteroid.getKineticEnergy().getMagnitude());
                    asteroidDetails.put("numberOfHiroshimaBombs", asteroid.getKineticEnergy().getHiroshimaBombs().getNumberOfBombs());
                    asteroidDetails.put("numberOfHiroshimaDeaths", asteroid.getKineticEnergy().getHiroshimaBombs().getNumberOfDeaths());

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

    private Asteroid toAsteroid(NearEarthObjectDto asteroidDto) {
        MissDistances missDistances = toMissDistances(asteroidDto.close_approach_data);
        KineticEnergy kineticEnergy = toKineticEnergy(asteroidDto);

        Id id = new Id(asteroidDto.id);
        Name name = new Name(asteroidDto.name);
        return new Asteroid(id, name, missDistances, kineticEnergy);
    }

    private KineticEnergy toKineticEnergy(NearEarthObjectDto asteroidDto) {
        Measures measures = new Measures(
                asteroidDto.estimated_diameter.meters.estimated_diameter_min,
                asteroidDto.estimated_diameter.meters.estimated_diameter_max);

        Velocities velocities = toVelocities(asteroidDto.close_approach_data);

        return new KineticEnergy(measures, velocities);
    }

    private Velocities toVelocities(List<CloseApproachDataDto> close_approach_data) {
        return new Velocities(close_approach_data.stream()
                .map(d -> d.relative_velocity)
                .map(d -> d.kilometers_per_second)
                .mapToDouble(Double::parseDouble)
                .mapToObj(Velocity::new)
                .collect(toList()));
    }

    private MissDistances toMissDistances(List<CloseApproachDataDto> closeApproachData) {
        return new MissDistances(
                closeApproachData.stream()
                        .map(a -> a.miss_distance)
                        .map(m -> m.kilometers)
                        .mapToDouble(Double::parseDouble)
                        .mapToObj(MissDistance::new)
                        .collect(toList()));
    }

}
