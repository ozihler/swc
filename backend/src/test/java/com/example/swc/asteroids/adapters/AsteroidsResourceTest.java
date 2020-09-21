package com.example.swc.asteroids.adapters;

import com.example.swc.asteroids.domain.IllegalRetrievalDateException;
import com.example.swc.asteroids.surrounding_systems.AsteroidsApiDataDto;
import com.example.swc.asteroids.surrounding_systems.NewWsApi;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testAverageLunarDistance() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "classpath:test_data/test_asteroids.json")
        );

        ResponseEntity<Map<String, List<Map<String, Object>>>> response = asteroidsResource.getDestructiveInformationOfAsteroids(
                "2020-09-07",
                "2020-09-08",
                false
        );

        var expectedAverageMissDistanceInKm = Set.of(
                28900622.06721149 / 384400d,
                29428735.534641825 / 384400d,
                28524696.487624039 / 384400d,
                7208071.387689618 / 384400d,
                45576240.477071092 / 384400d,
                19802196.225351155 / 384400d,
                49767255.023890832 / 384400d,
                12357224.642686937 / 384400d
        );

        var actualAverageMissDistanceInKm = response.getBody()
                .get("asteroids")
                .stream()
                .map(a -> a.get("averageLunarDistance"))
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
    void throwsIllegalRetrievalDateException() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "classpath:backup_data/backup_asteroids.json")
        );
        assertThrows(IllegalRetrievalDateException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("", "", true));
        assertThrows(IllegalRetrievalDateException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("2020-01-01", "", false));
        assertThrows(IllegalRetrievalDateException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("", "2020-01-01", true));
        assertThrows(IllegalRetrievalDateException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("dsafdsaf", "2020-01-01", false));
        assertThrows(IllegalRetrievalDateException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("2020-01-01", "fdsadsaf", true));
    }

    @Test
    void throwsRuntimeException() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "unkown.json")
        );

        assertThrows(RuntimeException.class, () -> asteroidsResource.getDestructiveInformationOfAsteroids("2000-01-01", "2000-01-01", true));
    }

    @Test
    void kineticEnergy() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "classpath:test_data/test_asteroids_2.json")
        );


        ResponseEntity<Map<String, List<Map<String, Object>>>> response = asteroidsResource.getDestructiveInformationOfAsteroids(
                "2020-09-07",
                "2020-09-08",
                false
        );


        var averageDiameterInMeters = (238.6464490278 + 533.6296826151) / 2d;
        var radius = averageDiameterInMeters / 2d;
        var volumeInCubicMeters = (4 / 3d) * Math.PI * radius;
        var massInKg = volumeInCubicMeters * 4000;
        var averageVelocityInMPerSeconds = 16.8314904753 * 1000;
        var kineticEnergyInJoules = 0.5 * massInKg * averageVelocityInMPerSeconds * averageVelocityInMPerSeconds;
        var expected = kineticEnergyInJoules * 0.00000000024;

        var actual = response.getBody()
                .get("asteroids")
                .stream()
                .map(a -> a.get("kineticEnergyInTonsOfTNT"))
                .mapToDouble((a -> Double.parseDouble(a.toString())))
                .findAny()
                .getAsDouble();

        assertEquals(
                expected,
                actual
        );
    }

    @Test
    void numberOfHiroshimaBombs() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "classpath:test_data/test_asteroids_2.json")
        );


        ResponseEntity<Map<String, List<Map<String, Object>>>> response = asteroidsResource.getDestructiveInformationOfAsteroids(
                "2020-09-07",
                "2020-09-08",
                false
        );


        var averageDiameterInMeters = (238.6464490278 + 533.6296826151) / 2d;
        var radius = averageDiameterInMeters / 2d;
        var volumeInCubicMeters = (4 / 3d) * Math.PI * radius;
        var massInKg = volumeInCubicMeters * 4000;
        var averageVelocityInMPerSeconds = 16.8314904753 * 1000;
        var kineticEnergyInJoules = 0.5 * massInKg * averageVelocityInMPerSeconds * averageVelocityInMPerSeconds;
        var kineticEnergyInTonsOfTnt = kineticEnergyInJoules * 0.00000000024;
        var expected = Math.round((float) kineticEnergyInTonsOfTnt / 15000f);

        var actual = response.getBody()
                .get("asteroids")
                .stream()
                .map(a -> a.get("numberOfHiroshimaBombs"))
                .mapToDouble((a -> Float.parseFloat(a.toString())))
                .findAny()
                .getAsDouble();

        assertEquals(
                expected,
                actual
        );
    }

    @Test
    void numberOfHiroshimaDeaths() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "classpath:test_data/test_asteroids_2.json")
        );


        ResponseEntity<Map<String, List<Map<String, Object>>>> response = asteroidsResource.getDestructiveInformationOfAsteroids(
                "2020-09-07",
                "2020-09-08",
                false
        );


        var averageDiameterInMeters = (238.6464490278 + 533.6296826151) / 2d;
        var radius = averageDiameterInMeters / 2d;
        var volumeInCubicMeters = (4 / 3d) * Math.PI * radius;
        var massInKg = volumeInCubicMeters * 4000;
        var averageVelocityInMPerSeconds = 16.8314904753 * 1000;
        var kineticEnergyInJoules = 0.5 * massInKg * averageVelocityInMPerSeconds * averageVelocityInMPerSeconds;
        var kineticEnergyInTonsOfTnt = kineticEnergyInJoules * 0.00000000024;
        var expected = Math.round((float) kineticEnergyInTonsOfTnt / 15000f) * 100000;

        var actual = response.getBody()
                .get("asteroids")
                .stream()
                .map(a -> a.get("numberOfHiroshimaDeaths"))
                .mapToDouble((a -> Integer.parseInt(a.toString())))
                .findAny()
                .getAsDouble();

        assertEquals(
                expected,
                actual
        );
    }

    @Test
    void addMagnitude() {
        HashMap<String, Object> asteroidDetails = new HashMap<>();
        AsteroidsResource.addMagnitude(asteroidDetails, 0.0);
        assertEquals("BELOW_TONS", asteroidDetails.get("magnitude"));
        AsteroidsResource.addMagnitude(asteroidDetails, 0.9999);
        assertEquals("BELOW_TONS", asteroidDetails.get("magnitude"));
        AsteroidsResource.addMagnitude(asteroidDetails, 1.0);
        assertEquals("TONS", asteroidDetails.get("magnitude"));
        AsteroidsResource.addMagnitude(asteroidDetails, 999.9999);
        assertEquals("TONS", asteroidDetails.get("magnitude"));
        AsteroidsResource.addMagnitude(asteroidDetails, 1000.0);
        assertEquals("KILO_TONS", asteroidDetails.get("magnitude"));
        AsteroidsResource.addMagnitude(asteroidDetails, 999999.9999);
        assertEquals("KILO_TONS", asteroidDetails.get("magnitude"));
        AsteroidsResource.addMagnitude(asteroidDetails, 1000000.0);
        assertEquals("MEGA_TONS", asteroidDetails.get("magnitude"));
        AsteroidsResource.addMagnitude(asteroidDetails, 999999999.9999);
        assertEquals("MEGA_TONS", asteroidDetails.get("magnitude"));
        AsteroidsResource.addMagnitude(asteroidDetails, 1000000000.0);
        assertEquals("ABOVE_MEGA_TONS", asteroidDetails.get("magnitude"));
        AsteroidsResource.addMagnitude(asteroidDetails, 10000000000.0);
        assertEquals("ABOVE_MEGA_TONS", asteroidDetails.get("magnitude"));
    }
}