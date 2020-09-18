package com.example.swc.asteroids.adapters;

import com.example.swc.asteroids.surrounding_systems.AsteroidsApiDataDto;
import com.example.swc.asteroids.surrounding_systems.NewWsApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AsteroidsResourceTest {

    private AsteroidsResource asteroidsResource;

    private class TestNewWsApi extends NewWsApi {
        public TestNewWsApi(String apiKey, String baseUrl) {
            super(apiKey, baseUrl);
        }

        @Override
        public AsteroidsApiDataDto getAsteroidData(Date startDate, Date endDate, boolean detailed, boolean useTestData) throws IOException {
            return testData();
        }
    }


    @BeforeEach
    void setUp() {
        asteroidsResource = new AsteroidsResource(
                new TestNewWsApi("","")
        );
    }

    @Test
    void test() {

    }

    @Test
    void throwsException() {
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("", "", true));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("2020-01-01", "", false));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("", "2020-01-01", true));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("dsafdsaf", "2020-01-01", false));
        assertThrows(IllegalArgumentException.class, () -> asteroidsResource.getMissingDistance("2020-01-01", "fdsadsaf", true));
    }
}