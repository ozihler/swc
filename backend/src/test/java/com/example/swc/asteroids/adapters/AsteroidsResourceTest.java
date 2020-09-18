package com.example.swc.asteroids.adapters;

import com.example.swc.asteroids.surrounding_systems.AsteroidsApiDataDto;
import com.example.swc.asteroids.surrounding_systems.NewWsApi;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;

class AsteroidsResourceTest {


    private class TestNewWsApi extends NewWsApi {
        public TestNewWsApi(String apiKey, String baseUrl, String testDataLocation) {
            super(apiKey, baseUrl, testDataLocation);
        }

        @Override
        public AsteroidsApiDataDto getAsteroidData(Date startDate, Date endDate, boolean detailed, boolean useTestData) throws IOException {
            return testData();
        }
    }


    @Test
    void testAverageMissDistanceInKm() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "classpath:test_data/test_asteroids.json")
        );

        ResponseEntity<Map<String, List<Map<String, Object>>>> response = asteroidsResource.getDestructiveInformationOfAsteroids(
                "2020-09-07",
                "2020-09-08",
                false
        );

        var expectedAverageMissDistanceInKm = Set.of(
                28900622.06721149,
                29428735.534641825,
                28524696.487624039,
                7208071.387689618,
                45576240.477071092,
                19802196.225351155,
                49767255.023890832,
                12357224.642686937
        );

        var actualAverageMissDistanceInKm = response.getBody()
                .get("asteroids")
                .stream()
                .map(a -> a.get("averageMissDistanceInKm"))
                .map(a -> Double.parseDouble(a.toString()))
                .collect(toSet());

        assertEquals(
                expectedAverageMissDistanceInKm,
                actualAverageMissDistanceInKm
        );
    }
    @Test
    void testAllIdsCollected() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "classpath:test_data/test_asteroids.json")
        );

        ResponseEntity<Map<String, List<Map<String, Object>>>> response = asteroidsResource.getDestructiveInformationOfAsteroids(
                "2020-09-07",
                "2020-09-08",
                false
        );

        var expected = Set.of(
                "54053725",
                "3696305",
                "54051304",
                "54053746",
                "3394070",
                "3599542",
                "3727273",
                "54050992"
        );

        var actual = response.getBody()
                .get("asteroids")
                .stream()
                .map(a -> a.get("id"))
                .collect(toSet());

        assertEquals(
                expected,
                actual
        );
    }

    @Test
    void throwsIllegalArgumentException() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "classpath:backup_data/backup_asteroids.json")
        );
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("", "", true));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("2020-01-01", "", false));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("", "2020-01-01", true));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("dsafdsaf", "2020-01-01", false));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("2020-01-01", "fdsadsaf", true));
    }

    @Test
    void throwsRuntimeException() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "unkown.json")
        );

        assertThrows(RuntimeException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("2000-01-01", "2000-01-01", true));
    }

}