package com.example.swc.asteroids.adapters.data_access;

import com.example.swc.asteroids.domain.*;
import com.example.swc.asteroids.surrounding_systems.AsteroidsApiDataDto;
import com.example.swc.asteroids.surrounding_systems.CloseApproachDataDto;
import com.example.swc.asteroids.surrounding_systems.NearEarthObjectDto;
import com.example.swc.asteroids.surrounding_systems.NewWsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Repository
public class AsteroidsRepository {

    private NewWsApi newWsApi;

    @Autowired
    public AsteroidsRepository(NewWsApi newWsApi) {
        this.newWsApi = newWsApi;
    }

    public Asteroids fetchAsteroids(RetrievalDate startDate, RetrievalDate endDate, boolean useTestData) throws IOException {
        AsteroidsApiDataDto dataFromApi = this.newWsApi.getAsteroidData(
                startDate.toDate(),
                endDate.toDate(),
                false,
                useTestData);

        List<Asteroid> asteroidList = new ArrayList<>();

        for (Map.Entry<String, List<NearEarthObjectDto>> asteroidsPerDate : dataFromApi.near_earth_objects.entrySet()) {
            for (NearEarthObjectDto asteroidDto : asteroidsPerDate.getValue()) {
                asteroidList.add(this.toAsteroid(asteroidDto));
            }
        }

        return new Asteroids(asteroidList);
    }

    private Asteroid toAsteroid(NearEarthObjectDto asteroidDto) {
        MissDistances missDistances = this.toMissDistances(asteroidDto.close_approach_data);
        KineticEnergy kineticEnergy = this.toKineticEnergy(asteroidDto);

        Id id = new Id(asteroidDto.id);
        Name name = new Name(asteroidDto.name);
        return new Asteroid(id, name, missDistances, kineticEnergy);
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

    private KineticEnergy toKineticEnergy(NearEarthObjectDto asteroidDto) {
        Measures measures = new Measures(
                asteroidDto.estimated_diameter.meters.estimated_diameter_min,
                asteroidDto.estimated_diameter.meters.estimated_diameter_max);

        Velocities velocities = this.toVelocities(asteroidDto.close_approach_data);

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
}
