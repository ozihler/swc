package com.example.swc.asteroids.adapters;

import com.example.swc.asteroids.surrounding_systems.AsteroidsApiDataDto;
import com.example.swc.asteroids.surrounding_systems.NewWsApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

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
    void test() {

    }

    @Test
    void throwsIllegalArgumentException() {

        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "classpath:backup_data/backup_asteroids.json")
        );
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("", "", true));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("2020-01-01", "", false));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("", "2020-01-01", true));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("dsafdsaf", "2020-01-01", false));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("2020-01-01", "fdsadsaf", true));
    }

    @Test
    void throwsRuntimeException() {
        var asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("", "", "unkown.json")
        );

        assertThrows(RuntimeException.class, () -> asteroidsResource.getMissingDistance("2000-01-01", "2000-01-01", true));

    }

}