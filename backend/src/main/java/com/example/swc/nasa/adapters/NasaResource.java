package com.example.swc.nasa.adapters;

import com.example.swc.nasa.surrounding_systems.AsteroidsDto;
import com.example.swc.nasa.surrounding_systems.DiameterDto;
import com.example.swc.nasa.surrounding_systems.NearEarthObjectDto;
import com.example.swc.nasa.surrounding_systems.NewWsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NasaResource {

    private NewWsApi newWsApi;

    @Autowired
    public NasaResource(NewWsApi newWsApi) {
        this.newWsApi = newWsApi;
    }

    @GetMapping("/nasa/asteroids")
    public ResponseEntity<AsteroidsDto> getAsteroids(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("isDetailed") boolean isDetailed
    ) throws IOException {
        AsteroidsDto data = this.newWsApi.getAsteroidData(startDate, endDate, isDetailed);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/nasa/asteroids/kineticEnergy")
    public ResponseEntity<Map<String, Double>> getMissingDistance(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) throws IOException {
        double jouleToTonOfTnt = 0.00000000024; // 1 joule = 0.00000000024 tons of TNT
        // Density * Volume (m3) = Mass (kg)
        // Volume = (4/3)*PI*R^3
        // Asteroid Densities: 8g/cm3 (Iron), 3g/cm3 (Chondrits) => Avg 4g/cm3
        // g/cm3 *1000 == kg/m3 ==> 4000 kg/m3
        double density = 4000;

        Map<String, Double> results = new HashMap<>();

        AsteroidsDto data = this.newWsApi.getAsteroidData(startDate, endDate, false);
        for (Map.Entry<String, List<NearEarthObjectDto>> asteroidsPerDate : data.near_earth_objects.entrySet()) {
            for (NearEarthObjectDto asteroid : asteroidsPerDate.getValue()) {
                DiameterDto meters = asteroid.estimated_diameter.meters;
                double averageDiameterInMeters = (meters.estimated_diameter_min + meters.estimated_diameter_max) / 2d;
                double radius = averageDiameterInMeters / 2;
                double volumeInCubicMeters = (4 / 3d) * Math.PI * radius;
                double massInKg = volumeInCubicMeters * density;

                double averageVelocityInMPerSecond = asteroid.close_approach_data
                        .stream()
                        .map(d -> d.relative_velocity)
                        .map(r -> r.kilometers_per_second)
                        .mapToDouble(Double::parseDouble)
                        .average()
                        .getAsDouble()
                        * 1000;

                double kineticEnergyInJoules = 0.5 * massInKg * averageVelocityInMPerSecond * averageVelocityInMPerSecond;
                double kineticEnergyInTonsOfTNT = kineticEnergyInJoules * jouleToTonOfTnt;
                results.put(asteroid.id, kineticEnergyInTonsOfTNT);
            }
        }
        // https://www.lpi.usra.edu/publications/books/barringer_crater_guidebook/chapter_11.pdf
        // https://www.real-world-physics-problems.com/asteroid-impact.html#:~:text=For%20example%2C%20consider%20an%20asteroid,2.8%C3%971020%20Joules.
        return ResponseEntity.ok(results);
    }
}
