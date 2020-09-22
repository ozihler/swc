package com.example.swc.asteroids.adapters;

import com.example.swc.asteroids.adapters.data_access.AsteroidsRepository;
import com.example.swc.asteroids.adapters.data_access.FetchAsteroids;
import com.example.swc.asteroids.adapters.presentation.RestAsteroidPresenter;
import com.example.swc.asteroids.application.use_cases.ViewDestructiveInformationOfAsteroids;
import com.example.swc.asteroids.domain.*;
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
    private final FetchAsteroids fetchAsteroids;

    @Autowired
    public AsteroidsResource(NewWsApi newWsApi) {
        this.newWsApi = newWsApi;
        fetchAsteroids = new AsteroidsRepository(newWsApi);
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
     * @param startDateString
     * @param endDateString
     * @param useTestData
     * @return
     */
    @GetMapping("/api/asteroids/kineticEnergy")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getDestructiveInformationOfAsteroids(
            @RequestParam("startDate") String startDateString,
            @RequestParam("endDate") String endDateString,
            @RequestParam("useTestData") boolean useTestData) {

        RetrievalDate startDate = new RetrievalDate(startDateString);
        RetrievalDate endDate = new RetrievalDate(endDateString);

        RestAsteroidPresenter output = new RestAsteroidPresenter();

        ViewDestructiveInformationOfAsteroids useCase = new ViewDestructiveInformationOfAsteroids(fetchAsteroids);

        useCase.invokeWith(useTestData, startDate, endDate, output);

        return output.getResponse();
    }

}
